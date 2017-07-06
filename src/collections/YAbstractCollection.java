package collections;

/**
 * Created by yaodh on 2017/7/3.
 */
public abstract class YAbstractCollection<E> implements YCollection<E> {
    protected YAbstractCollection() {}

    public abstract YIterator<E> iterator();
    public abstract int size();

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        YIterator<E> it = iterator();
        if (o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }
}
