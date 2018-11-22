package thinking_in_java.chap15_generics;

import java.util.ArrayList;
import java.util.LinkedList;

public class P192_Sequence {
    private Object[] items;
    private int next = 0;
    public P192_Sequence(int size) {
        items = new Object[size];
    }
    public void add(Object x) {
        if (next < items.length) {
            items[next++] = x;
        }
    }
    public Selector selector() {
        return new SequenceSelector();
    }
    private class SequenceSelector implements Selector {
        private int i = 0;
        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length) i++;
        }
    }

    public static void main(String[] args) {
        P192_Sequence sequence = new P192_Sequence(10);
        for (int i = 0; i < 10; i++) {
            sequence.add(Integer.toString(i));
        }
        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
        System.out.println();
        System.out.println(selector.getClass().getName());
    }
}
interface Selector {
    boolean end();
    Object current();
    void next();
}