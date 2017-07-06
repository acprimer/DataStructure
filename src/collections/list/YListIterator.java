package collections.list;

import java.util.Iterator;

/**
 * Created by yaodh on 2017/7/3.
 */
public interface YListIterator<E> extends Iterator<E> {
    @Override
    boolean hasNext();

    @Override
    E next();

    @Override
    void remove();

    boolean hasPrevious();

    E previous();

    int nextIndex();

    int previousIndex();

    void set(E e);

    void add(E e);
}
