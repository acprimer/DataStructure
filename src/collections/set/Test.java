package collections.set;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by yaodh on 2017/11/13.
 */
public class Test {
    public static void main(String[] args) {
        Order o1 = new Order(1);
        Order o2 = new Order(2);
        Set<Order> set = new TreeSet<>();
        set.add(o1);
        set.add(o2);
        System.out.println("set");
        for (Order o : set) {
            System.out.println(o.order);
        }

        Map<Order, Integer> map = new HashMap();
        map.put(o1, o1.order);
        map.put(o1, o2.order);
        System.out.println("map");
        for (Map.Entry<Order, Integer> e : map.entrySet()) {
            System.out.println(e.getValue());
        }
    }

    static class Order implements Comparable<Order> {
        private int order;

        public Order(int order) {
            this.order = order;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int compareTo(Order o) {
            return 0;
        }
    }
}
