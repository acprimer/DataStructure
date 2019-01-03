package collections.list;

import collections.map.Utils;

import java.util.Deque;

public class YArrayDeque<E> {
    private transient E[] elements;
    private transient int head;
    private transient int tail;
    private static final int MIN_INITIAL_CAPACITY = 8;

    private void allocateElements(int numElements) {
        int initialCapacity = MIN_INITIAL_CAPACITY;
        if (numElements >= initialCapacity) {
            initialCapacity = Utils.roundUpToPowerOfTwo(numElements);
        }
        elements = (E[]) new Object[initialCapacity];
    }

    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p;
        int newCapacity = n << 1;
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, deque too big");
        Object[] a = new Object[newCapacity];
        // 分成两段拷贝 0...head...n-1
        // head...n-1
        System.arraycopy(elements, p, a, 0, r);
        // 0...head
        System.arraycopy(elements, 0, a, r, p);
        elements = (E[]) a;
        head = 0;
        tail = n;
    }

    private <T> T[] copyElements(T[] a) {
        if (head < tail) {
            System.arraycopy(elements, head, a, 0, size());
        } else if (head > tail) {
            int headPortionLen = elements.length - head;
            System.arraycopy(elements, headPortionLen, a, 0, headPortionLen);
            System.arraycopy(elements, 0, a, headPortionLen, tail);
        }
        return a;
    }

    public int size() {
        return 0;
    }
}
