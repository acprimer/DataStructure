package collections.map;

/**
 * Created by yaodh on 2018/2/2.
 */
public class ResizeInfiniteLoopTest {
    private class Entry {
        int value;
        Entry next;

        public Entry(int value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }

    void transfer(Entry[] oldTable, Entry[] newTable) {
        int newCapacity = newTable.length;
        for (Entry e : oldTable) {
            while (e != null) {
                Entry next = e.next;
                int i = e.value % newCapacity;
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }

    void rehash() {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        int newCapacity = oldCapacity << 1;
        Entry[] newTable = new Entry[newCapacity];
        int sizeMask = newCapacity - 1;
        for (int i = 0; i < oldCapacity; i++) {
            Entry e = oldTable[i];
            if (e != null) {
                Entry next = e.next;
                int idx = e.value & sizeMask;
                if (next == null) {
                    newTable[idx] = e;
                } else {
                    Entry lastRun = e;
                    int lastIdx = idx;
                    for (Entry last = next; last != null; last = lastRun.next) {
                        int k = last.value & sizeMask;
                        if (k != lastIdx) {
                            lastIdx = k;
                            lastRun = last;
                        }
                    }
                    newTable[lastIdx] = lastRun;
                    for (Entry p = e; p != lastRun; p = p.next) {
                        int k = p.value & sizeMask;
                        Entry n = newTable[k];
                        newTable[k] = new Entry(p.value, n);
                    }
                }
            }
        }
//        int nodeIndex = node.value & sizeMask;
//        node.next = newTable[nodeIndex];
//        newTable[nodeIndex] = node;
        table = newTable;
    }

    private Entry[] table;

    void test() {
        Entry[] oldTable = new Entry[2];
        Entry[] newTable = new Entry[4];
        Entry e9 = new Entry(9, null);
        Entry e7 = new Entry(7, e9);
        Entry e5 = new Entry(5, e7);
        Entry e3 = new Entry(3, e5);
        oldTable[1] = e3;
        table = oldTable;
        rehash();
        System.out.println(table[1].value);
        System.out.println(table[1].next.value);
        System.out.println(table[3].value);
        System.out.println(table[3].next.value);
//        transfer(oldTable, newTable);

//        Unsafe unsafe = utils.UnsafeUtils.getUnsafe();
//        Class tc = Entry[].class;
//        int base = unsafe.arrayBaseOffset(tc);
//        int indexScale = unsafe.arrayIndexScale(tc);
//        System.out.println(base);
//        System.out.println(indexScale);
//
//        Entry entry = (Entry) unsafe.getObjectVolatile(oldTable, base + indexScale);
//        System.out.println("v " + entry.value);
    }

    public static void main(String[] args) {
        new ResizeInfiniteLoopTest().test();

//        System.out.println(31-Integer.numberOfLeadingZeros(4));
    }
}
