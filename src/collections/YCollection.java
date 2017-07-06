package collections;

/**
 * Created by yaodh on 2017/7/2.
 */
public interface YCollection<E> extends YIterable<E> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    @Override
    YIterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] a);

    boolean add(E e);

    boolean remove(Object o);

    boolean containsAll(YCollection<?> c);
}
