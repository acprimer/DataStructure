package collections.map;

/**
 * Created by yaodh on 2017/8/20.
 */
public class SparseArray<E> {
    private static final Object DELETE = new Object();
    private boolean mGarbage = false;
    private int[] mKeys;
    private Object[] mValues;
    private int mSize;

    public SparseArray() {
        this(10);
    }

    public SparseArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            mKeys = new int[0];
            mValues = new Object[0];
        } else {
            mKeys = new int[initialCapacity];
            mValues = new Object[mKeys.length];
        }

        mSize = 0;
    }

    public E get(int key) {
        return get(key, null);
    }

    public E get(int key, E valueIfKeyNotFound) {
        int idx = ArrayUtils.binarySearch(mKeys, mSize, key);
        if (idx < 0 || mValues[idx] == DELETE) {
            return valueIfKeyNotFound;
        } else {
            return (E) mValues[idx];
        }
    }

    public void delete(int key) {
        int idx = ArrayUtils.binarySearch(mKeys, mSize, key);
        if (idx >= 0 && mValues[idx] != DELETE) {
            mValues[idx] = DELETE;
            mGarbage = true;
        }
    }

    public E removeReturnOld(int key) {
        int idx = ArrayUtils.binarySearch(mKeys, mSize, key);
        if (idx >= 0 && mValues[idx] != DELETE) {
            final E old = (E) mValues[idx];
            mValues[idx] = DELETE;
            mGarbage = true;
            return old;
        }
        return null;
    }

    public void remove(int key) {
        delete(key);
    }

    public void removeAt(int index) {
        if (mValues[index] != DELETE) {
            mValues[index] = DELETE;
            mGarbage = true;
        }
    }

    public void removeAtRange(int index, int size) {
        final int end = Math.min(mSize, index + size);
        for (int i = index; i < end; i++) {
            remove(i);
        }
    }

    private void gc() {
        int n = mSize;
        int k = 0;
        int[] keys = mKeys;
        Object[] values = mValues;
        for (int i = 0; i < n; i++) {
            Object val = values[i];
            if (val != DELETE) {
                if (i != k) {
                    keys[k] = keys[i];
                    values[k] = val;
                    values[i] = null;
                }
                k++;
            }
        }

        mGarbage = false;
        mSize = k;
    }

    public void put(int key, E val) {
        int idx = ArrayUtils.binarySearch(mKeys, mSize, key);

        if (idx >= 0) {
            mValues[idx] = val;
        } else {
            idx = ~idx;

            if (idx < mSize && mValues[idx] == DELETE) {
                mKeys[idx] = key;
                mValues[idx] = val;
                return;
            }

            if (mGarbage && mSize >= mKeys.length) {
                gc();
                idx = ~ArrayUtils.binarySearch(mKeys, mSize, key);
            }

            mKeys = ArrayUtils.insert(mKeys, mSize, idx, key);
            mValues = ArrayUtils.insert(mValues, mSize, idx, val);
            mSize++;
        }
    }

    public int size() {
        if (mGarbage) {
            gc();
        }

        return mSize;
    }

    public int keyAt(int index) {
        if (mGarbage) {
            gc();
        }

        return mKeys[index];
    }

    @SuppressWarnings("unchecked")
    public E valueAt(int index) {
        if (mGarbage) {
            gc();
        }

        return (E) mValues[index];
    }

    public void setValueAt(int index, E value) {
        if (mGarbage) {
            gc();
        }

        mValues[index] = value;
    }

    public int indexOfKey(int key) {
        if (mGarbage) {
            gc();
        }

        return ArrayUtils.binarySearch(mKeys, mSize, key);
    }

    public int indexOfValue(E value) {
        if (mGarbage) {
            gc();
        }

        for (int i = 0; i < mSize; i++)
            if (mValues[i] == value)
                return i;

        return -1;
    }

    /**
     * Removes all key-value mappings from this SparseArray.
     */
    public void clear() {
        int n = mSize;
        Object[] values = mValues;

        for (int i = 0; i < n; i++) {
            values[i] = null;
        }

        mSize = 0;
        mGarbage = false;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }

        StringBuilder buffer = new StringBuilder(mSize * 28);
        buffer.append('{');
        for (int i=0; i<mSize; i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            int key = keyAt(i);
            buffer.append(key);
            buffer.append('=');
            Object value = valueAt(i);
            if (value != this) {
                buffer.append(value);
            } else {
                buffer.append("(this Map)");
            }
        }
        buffer.append('}');
        return buffer.toString();
    }
}
