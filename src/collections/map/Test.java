package collections.map;

/**
 * Created by yaodh on 2018/2/2.
 */
public class Test {
    private class Entry {
        int value;
        Entry next;

        public Entry(int value, Entry next) {
            this.value = value;
            this.next = next;
        }
    }

    Entry reverse(Entry head) {
        Entry newHead = null;
        Entry e = head;
        int i = 0;
        do {
             Entry next = e.next;
             if (i == 0) {
                 Entry t = e;
                 e = next;
                 next = t;
             }
             i++;
             e.next = newHead;
             newHead = e;
             e = next;
        } while (e != null);
        return newHead;
    }

    /**
     * ConcurrentHashMap 1.7的扩容代码
     */
    Entry[] rehash(Entry[] table) {
        Entry[] oldTable = table;
        int oldCapacity = table.length;
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
                    Entry lastRun = next;
                    int lastIdx = idx;
                    for (Entry last = next; last != null; last = last.next) {
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
        return newTable;
    }

    private Entry[] generate() {
        Entry[] table = new Entry[8];
        Entry[] list = new Entry[7];
        for (int i = 0; i < 7; i++) {
            int v = i * 8 + 1;
            list[i] = new Entry(v, null);
        }
        for (int i = 0; i < 6; i++) {
            list[i].next = list[i+1];
        }
        list[6].value = 57;
        table[1] = list[0];
        return table;
    }

    void test() {
//        // e3 -> e7 -> null
//        Entry e7 = new Entry(7, null);
//        Entry e3 = new Entry(3, e7);
//
//        // reverse: e7 <-> e3
//        Entry head = reverse(e3);
//        while (head != null) {
//            System.out.println(head.value);
//            head = head.next;
//        }
        Entry[] table = generate();
        table = rehash(table);

        printTable(table);
    }

    void printTable(Entry[] table) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry p = table[i];
                System.out.print(i + " ");
                while (p != null) {
                    System.out.print(p.value);
                    p = p.next;
                    if (p != null) {
                        System.out.print("->");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new Test().test();
    }
}
