package collections.map;

import java.io.*;
import java.util.*;

/**
 * Created by yaodh on 2016/10/10.
 */
public class HashMap<K, V> extends AbstractMap<K, V>
        implements Serializable, Cloneable {

    private static final int MINIMUM_CAPACITY = 4;
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private static final Entry[] EMPTY_TABLE
            = new HashMapEntry[MINIMUM_CAPACITY >>> 1];

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    transient HashMapEntry<K, V>[] table;
    transient HashMapEntry<K, V> entryForNullKey;

    transient int size;
    transient int modCount;

    private transient int threshold;
    private transient Set<K> keySet;
    private transient Set<Entry<K, V>> entrySet;
    private transient Collection<V> values;

    public HashMap() {
        table = (HashMapEntry<K, V>[]) EMPTY_TABLE;
        threshold = -1;
    }

    public HashMap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity: " + capacity);
        }

        if (capacity == 0) {
            table = (HashMapEntry<K, V>[]) EMPTY_TABLE;
            threshold = -1;
            return;
        }

        if (capacity < MINIMUM_CAPACITY) {
            capacity = MINIMUM_CAPACITY;
        } else if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        } else {
            capacity = Utils.roundUpToPowerOfTwo(capacity);
        }

        makeTable(capacity);
    }

    private HashMapEntry<K, V>[] makeTable(int newCapacity) {
        HashMapEntry<K, V>[] newTable = new HashMapEntry[newCapacity];
        table = newTable;
        threshold = (newCapacity >> 1) + (newCapacity >> 2); // 0.75 capacity
        return newTable;
    }

    private HashMapEntry<K, V>[] doubleCapacity() {
        HashMapEntry<K, V>[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            return oldTable;
        }

        int newCapacity = oldCapacity << 1;
        HashMapEntry<K, V>[] newTable = makeTable(newCapacity);
        if (size == 0) {
            return newTable;
        }

        for (int j = 0; j < oldCapacity; j++) {
            HashMapEntry<K, V> e = oldTable[j];
            if (e == null) {
                continue;
            }
            int highBit = e.hash & oldCapacity;
            HashMapEntry<K, V> broken = null;
            newTable[j | highBit] = e;
            for (HashMapEntry<K, V> n = e.next; n != null; e = n, n = n.next) {
                int nextHighBit = n.hash & oldCapacity;
                if (nextHighBit != highBit) {
                    if (broken == null) {
                        newTable[j | nextHighBit] = n;
                    } else {
                        broken.next = n;
                    }
                    broken = e;
                    highBit = nextHighBit;
                }
            }
            if (broken != null) {
                broken.next = null;
            }
        }
        return newTable;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            return putValueForNullKey(value);
        }

        int hash = Utils.secondaryHash(key);
        HashMapEntry<K, V>[] tab = table;
        int index = hash & (tab.length - 1);
        System.out.println(index);
        for (HashMapEntry<K, V> e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash && key.equals(e.key)) {
                preModify(e);
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        modCount++;
        if (size++ > threshold) {
            tab = doubleCapacity();
            index = hash & (tab.length - 1);
        }
        addNewEntry(key, value, hash, index);
        return null;
    }


    private V putValueForNullKey(V value) {
        HashMapEntry<K, V> entry = entryForNullKey;
        if (entry == null) {
            addNewEntryForNullKey(value);
            size++;
            modCount++;
            return null;
        } else {
            preModify(entry);
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }
    }

    private void preModify(HashMapEntry<K, V> entry) {
        // empty
    }

    void addNewEntry(K key, V value, int hash, int index) {
        table[index] = new HashMapEntry<>(key, value, hash, table[index]);
    }

    void addNewEntryForNullKey(V value) {
        entryForNullKey = new HashMapEntry<>(null, value, 0, null);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        super.putAll(map);
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            HashMapEntry<K, V> e = entryForNullKey;
            return e == null ? null : e.value;
        }

        int hash = Utils.secondaryHash(key);
        HashMapEntry<K, V>[] tab = table;
        for (HashMapEntry<K, V> e = tab[hash & (tab.length - 1)];
             e != null; e = e.next) {
            K eKey = e.key;
            if (eKey == key || (e.hash == hash && key.equals(eKey))) {
                return e.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null) {
            return entryForNullKey != null;
        }

        int hash = Utils.secondaryHash(key);
        HashMapEntry<K, V>[] tab = table;
        for (HashMapEntry<K, V> e = tab[hash & (tab.length - 1)];
             e != null; e = e.next) {
            K eKey = e.key;
            if (eKey == key || (e.hash == hash && key.equals(eKey))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        HashMapEntry<K, V>[] tab = table;
        if (value == null) {
            for (int i = 0; i < tab.length; i++) {
                for (HashMapEntry e = tab[i]; e != null; e = e.next) {
                    if (e.value == null) {
                        return true;
                    }
                }
            }
            return entryForNullKey != null && entryForNullKey.value == null;
        }

        for (int i = 0; i < tab.length; i++) {
            for (HashMapEntry e = tab[i]; e != null; e = e.next) {
                if (value.equals(e.value)) {
                    return true;
                }
            }
        }
        return entryForNullKey != null && value.equals(entryForNullKey.value);
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        if (entrySet == null) {
            entrySet = new EntrySet();
        }
        return entrySet;
    }

    private static class HashMapEntry<K, V> implements Entry<K, V> {
        final K key;
        V value;
        final int hash;
        HashMapEntry<K, V> next;

        HashMapEntry(K key, V value, int hash, HashMapEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public final V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry<?, ?> e = (Entry<?, ?>) obj;
            return Objects.equals(e.getKey(), key)
                    && Objects.equals(e.getValue(), value);
        }

        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private abstract class HashIterator {
        int nextIndex;
        HashMapEntry<K, V> nextEntry = entryForNullKey;
        HashMapEntry<K, V> lastEntryReturned;
        int expectedModCount = modCount;

        HashIterator() {
            if (nextEntry == null) {
                HashMapEntry<K, V>[] tab = table;
                HashMapEntry<K, V> next = null;
                while (next == null && nextIndex < tab.length) {
                    next = tab[nextIndex++];
                }
                nextEntry = next;
            }
        }

        public boolean hasNext() {
            return nextEntry != null;
        }

        HashMapEntry<K, V> nextEntry() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (nextEntry == null) {
                throw new NoSuchElementException();
            }

            HashMapEntry<K, V> entryToReturn = nextEntry;
            HashMapEntry<K, V>[] tab = table;
            HashMapEntry<K, V> next = entryToReturn.next;
            while (next == null && nextIndex < tab.length) {
                next = tab[nextIndex++];
            }
            nextEntry = next;
            return lastEntryReturned = entryToReturn;
        }

        public void remove() {
            if (lastEntryReturned == null) {
                throw new IllegalStateException();
            }
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            HashMap.this.remove(lastEntryReturned.key);
            lastEntryReturned = null;
            expectedModCount = modCount;
        }
    }

    private final class KeyIterator extends HashIterator implements Iterator<K> {

        @Override
        public K next() {
            return nextEntry().key;
        }
    }

    private final class ValueIterator extends HashIterator implements Iterator<V> {

        @Override
        public V next() {
            return nextEntry().value;
        }
    }

    private final class EntryIterator extends HashIterator
            implements Iterator<Entry<K, V>> {
        public Entry<K, V> next() {
            return nextEntry();
        }
    }

    private boolean containsMapping(Object key, Object value) {
        if (key == null) {
            HashMapEntry<K, V> e = entryForNullKey;
            return e != null && Objects.equals(value, e.value);
        }

        int hash = Utils.secondaryHash(key);
        HashMapEntry<K, V>[] tab = table;
        int index = hash & (tab.length - 1);
        for (HashMapEntry<K, V> e = tab[index]; e != null; e = e.next) {
            if (e.hash == hash && key.equals(e.key)) {
                return Objects.equals(value, e.value);
            }
        }
        return false;
    }

    private boolean removeMapping(Object key, Object value) {
        return false;
    }

    private final class KeySet extends AbstractSet<K> {

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public boolean contains(Object o) {
            return containsKey(o);
        }

        public boolean remove(Object o) {
            int oldSize = size;
            HashMap.this.remove(o);
            return size != oldSize;
        }

        @Override
        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void clear() {
            HashMap.this.clear();
        }

    }

    private final class Values extends AbstractCollection<V> {

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override
        public boolean contains(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?, ?> e = (Entry<?, ?>) o;
            return containsMapping(e.getKey(), e.getValue());
        }

        @Override
        public boolean remove(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?, ?> e = (Entry<?, ?>) o;
            return removeMapping(e.getKey(), e.getValue());
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        public void clear() {
            HashMap.this.clear();
        }
    }

    private final class EntrySet extends AbstractSet<Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public boolean contains(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?, ?> e = (Entry<?, ?>) o;
            return containsMapping(e.getKey(), e.getValue());
        }

        public boolean remove(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?, ?> e = (Entry<?, ?>) o;
            return removeMapping(e.getKey(), e.getValue());
        }

        @Override
        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void clear() {
            HashMap.this.clear();
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        ObjectOutputStream.PutField fields = stream.putFields();
        fields.put("loadFactor", DEFAULT_LOAD_FACTOR);
        stream.writeFields();

        stream.writeInt(table.length);
        stream.writeInt(size);
        for (Entry<K, V> e : entrySet()) {
            stream.writeObject(e.getKey());
            stream.writeObject(e.getValue());
        }
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int capacity = stream.readInt();
        if (capacity < 0) {
            throw new InvalidObjectException("Capacity: " + capacity);
        }
        if (capacity < MINIMUM_CAPACITY) {
            capacity = MINIMUM_CAPACITY;
        } else if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        } else {
            capacity = Utils.roundUpToPowerOfTwo(capacity);
        }
        makeTable(capacity);

        int size = stream.readInt();
        if (size < 0) {
            throw new InvalidObjectException("Size: " + size);
        }

        for (int i = 0; i < size; i++) {
            K key = (K) stream.readObject();
            V val = (V) stream.readObject();
            put(key, val);
        }
    }

}
