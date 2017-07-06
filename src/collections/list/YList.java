package collections.list;

import collections.YCollection;
import collections.YIterator;

/**
 * Created by yaodh on 2017/7/3.
 */
public interface YList<E> extends YCollection<E> {
    @Override
    int size();

    @Override
    boolean isEmpty();

    @Override
    boolean contains(Object o);

    @Override
    YIterator<E> iterator();

    @Override
    Object[] toArray();

    @Override
    <T> T[] toArray(T[] a);

    @Override
    boolean add(E e);

    @Override
    boolean remove(Object o);

    @Override
    boolean containsAll(YCollection<?> c);

    // Positional Access Operations

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    YListIterator<E> listIterator();
    YListIterator<E> listIterator(int index);

    YList<E> subList(int fromIndex, int toIndex);
}
