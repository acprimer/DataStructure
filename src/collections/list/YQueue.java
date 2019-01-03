package collections.list;

import collections.YCollection;

public interface YQueue<E> extends YCollection<E> {
    boolean add(E e);
    boolean offer(E e);
    E remove();
    E poll();
    E element();
    E peek();
}
