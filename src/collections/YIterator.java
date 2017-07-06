package collections;

/**
 * Created by yaodh on 2017/7/2.
 */
public interface YIterator<E> {
    boolean hasNext();
    E next();
    void remove();
}
