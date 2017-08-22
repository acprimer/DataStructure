package threadlocal;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yaodh on 2017/8/13.
 */
public class YThreadLocal<T> {
    private static AtomicInteger nextHashCode = new AtomicInteger();
    private static final int HASH_INCREMENT = 0x61c88647;

    private final int threadLocalHashCode = nextHashCode();

    private static int nextHashCode() {
//        return nextHashCode.addAndGet(HASH_INCREMENT);
        return 0;
    }

    /* hide */
    public int getHashCode() {
        return threadLocalHashCode;
    }

    // default initial value, should rewrite
    protected T initialValue() {
        return null;
    }

    // empty constructor
    public YThreadLocal() { }

    public T get() {
        Thread t = Thread.currentThread();
        if (!(t instanceof YThread)) return null;

        YThreadLocalMap map = getMap(t);
        if (map != null) {
            YThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null)
                return (T) e.value;
        }
        return setInitialValue();
    }

    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        YThreadLocalMap map = getMap(t);
        if (map != null) map.set(this, value);
        else createMap(t, value);
        return value;
    }

    public void set(T value) {
        Thread t = Thread.currentThread();
        if (!(t instanceof YThread)) return;

        YThreadLocalMap map = getMap(t);
        if (map != null) map.set(this, value);
        else createMap(t, value);
    }

    YThreadLocalMap getMap(Thread t) {
        return ((YThread)t).threadLocals;
    }

    void createMap(Thread t, T firstValue) {
        ((YThread)t).threadLocals = new YThreadLocalMap(this, firstValue);
    }

    static class YThreadLocalMap {
        static class Entry extends WeakReference<YThreadLocal> {
            Object value;

            Entry(YThreadLocal key, Object value) {
                super(key);
                this.value = value;
            }
        }

        private static final int INITIAL_CAPACITY = 16;
        private Entry[] table;
        private int size = 0;
        private int threshold; // Default to 0
        private void setThreshold(int len) {
            threshold = len * 2 / 3;
        }
        private static int nextIndex(int idx, int len) {
            return ((idx + 1 < len) ? (idx + 1) : 0);
        }
        private static int prevIndex(int idx, int len) {
            return ((idx - 1 >= 0) ? (idx - 1) : (len - 1));
        }

        YThreadLocalMap(YThreadLocal firstKey, Object firstValue) {
            table = new Entry[INITIAL_CAPACITY];
            int idx = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
            table[idx] = new Entry(firstKey, firstValue);
            size = 1;
            setThreshold(INITIAL_CAPACITY);
        }

        private Entry getEntry(YThreadLocal key) {
            int idx = key.threadLocalHashCode & (table.length - 1);
            Entry e = table[idx];
            if (e != null && e.get() == key)
                return e;
            else
                return getEntryAfterMiss(key, idx, e);
        }

        private Entry getEntryAfterMiss(YThreadLocal key, int idx, Entry e) {
            Entry[] tab = table;
            int len = tab.length;

            while (e != null) {
                YThreadLocal k = e.get();
                if (k == key)
                    return e;
                if (k == null)
                    expungeStaleEntry(idx);
                else
                    idx = nextIndex(idx, len);

                e = tab[idx];
            }
            return null;
        }

        private void set(YThreadLocal key, Object value) {
            Entry[] tab = table;
            int len = tab.length;
            int idx = key.threadLocalHashCode & (len - 1);

            for (Entry e = tab[idx]; e != null; e = tab[idx = nextIndex(idx, len)]) {
                YThreadLocal k = e.get();
                if (k == key) {
                    e.value = value;
                    return;
                }
                if (k == null) {
                    replaceStaleEntry(key, value, idx);
                    return;
                }
            }

            tab[idx] = new Entry(key, value);
            int sz = ++size;
            if (!cleanSomeSlots(idx, sz) && sz >= threshold) {
                rehash();
            }
        }

        private void replaceStaleEntry(YThreadLocal key, Object value, int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;
            Entry e;

            int slotToExpunge = staleSlot;
            for (int i = prevIndex(staleSlot, len); (e = tab[i]) != null; i = prevIndex(i, len)) {
                if (e.get() == null) {
                    slotToExpunge = i;
                }
            }

            for (int i = nextIndex(staleSlot, len); (e = tab[i]) != null; i = nextIndex(i, len)) {
                YThreadLocal k = e.get();

                if (k == key) {
                    e.value = value;
                    tab[i] = tab[staleSlot];
                    tab[staleSlot] = e;

                    if (slotToExpunge == staleSlot) {
                        slotToExpunge = i;
                    }
                    cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
                    return;
                }

                if (k == null && slotToExpunge == staleSlot) {
                    slotToExpunge = i;
                }
            }

            tab[staleSlot].value = null;
            tab[staleSlot] = new Entry(key, value);

            if (slotToExpunge != staleSlot) {
                cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
            }
        }

        private int expungeStaleEntry(int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;

            tab[staleSlot].value = null;
            tab[staleSlot] = null;
            size--;

            Entry e;
            int i;
            for (i = nextIndex(staleSlot, len); (e = tab[i]) != null; i = nextIndex(i, len)) {
                YThreadLocal k = e.get();
                if (k == null) {
                    e.value = null;
                    tab[i] = null;
                    size--;
                } else {
                    int h = k.threadLocalHashCode & (len - 1);
                    if (h != i) {
                        tab[i] = null;
                        while (tab[h] != null) {
                            h = nextIndex(h, len);
                        }
                        tab[h] =e;
                    }
                }
            }
            return i;
        }

        private boolean cleanSomeSlots(int idx, int n) {
            boolean removed = false;
            Entry[] tab = table;
            int len = tab.length;
            do {
                idx = nextIndex(idx, len);
                Entry e = tab[idx];
                if (e != null && e.get() == null) {
                    n = len;
                    removed = true;
                    idx = expungeStaleEntry(idx);
                }
            } while ((n >>>= 1) != 0);
            return removed;
        }

        private void rehash() {
            expungeStaleEntries();

            if (size >= threshold - threshold / 4) {
                resize();
            }
        }

        private void resize() {
            Entry[] oldTab = table;
            int oldLen = oldTab.length;
            int newLen = oldLen * 2;
            Entry[] newTab = new Entry[newLen];
            int count = 0;

            for (int j = 0; j < oldLen; j++) {
                Entry e = oldTab[j];
                if (e != null) {
                    YThreadLocal k = e.get();
                    if (k == null) {
                        e.value = null; // help the GC
                    } else {
                        int h = k.threadLocalHashCode & (newLen - 1);
                        while (newTab[h] != null) {
                            h = nextIndex(h, newLen);
                        }
                        newTab[h] = e;
                        count++;
                    }
                }
            }

            setThreshold(newLen);
            size = count;
            table = newTab;
        }

        private void expungeStaleEntries() {
            Entry[] tab = table;
            int len = tab.length;
            for (int j = 0; j < len; j++) {
                Entry e = tab[j];
                if (e != null && e.get() == null) {
                    expungeStaleEntry(j);
                }
            }
        }
    }
}
