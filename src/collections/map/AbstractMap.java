package collections.map;

import java.util.*;

/**
 * Created by yaodh on 2016/10/10.
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {
    Set<K> keySet;
    Collection<V> valuesCollection;

    protected AbstractMap() {

    }

    @Override
    public void clear() {
        entrySet().clear();
    }

    @Override
    public boolean containsKey(Object key) {
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        if (key != null) {
            while (it.hasNext()) {
                if (key.equals(it.next().getKey())) {
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (it.next().getKey() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        if (value != null) {
            while (it.hasNext()) {
                if (value.equals(it.next().getValue())) {
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (it.next().getValue() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public abstract Set<Entry<K, V>> entrySet();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) obj;
            if (size() != map.size()) {
                return false;
            }

            try {
                for (Entry<K, V> entry : entrySet()) {
                    K key = entry.getKey();
                    V mine = entry.getValue();
                    Object theirs = map.get(key);
                    if (mine == null) {
                        if (theirs != null || !map.containsKey(key)) {
                            return false;
                        }
                    } else if (!mine.equals(theirs)) {
                        return false;
                    }
                }
            } catch (NullPointerException ignored) {
                return false;
            } catch (ClassCastException ignored) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        Iterator<Entry<K, V>> it = entrySet().iterator();
        if (key != null) {
            while (it.hasNext()) {
                Entry<K, V> entry = it.next();
                if (key.equals(entry.getKey())) {
                    return entry.getValue();
                }
            }
        } else {
            while (it.hasNext()) {
                Entry<K, V> entry = it.next();
                if (entry.getKey() == null) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        int result = 0;
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        while (it.hasNext()) {
            result += it.next().hashCode();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Set<K> keySet() {
        if (keySet == null) {
            keySet = new AbstractSet<K>() {
                @Override
                public boolean contains(Object o) {
                    return containsKey(o);
                }

                @Override
                public Iterator<K> iterator() {
                    return new Iterator<K>() {
                        Iterator<Entry<K, V>> setIterator = entrySet().iterator();

                        @Override
                        public boolean hasNext() {
                            return setIterator.hasNext();
                        }

                        @Override
                        public K next() {
                            return setIterator.next().getKey();
                        }

                        @Override
                        public void remove() {
                            setIterator.remove();
                        }
                    };
                }

                @Override
                public int size() {
                    return AbstractMap.this.size();
                }
            };
        }
        return keySet;
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for(Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        if(key != null) {
            while (it.hasNext()) {
                Map.Entry<K, V> entry = it.next();
                if(key.equals(entry.getKey())) {
                    it.remove();
                    return entry.getValue();
                }
            }
        } else {
            while (it.hasNext()) {
                Map.Entry<K, V> entry = it.next();
                if(entry.getKey() == null) {
                    it.remove();
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public String toString() {
        if(isEmpty()) {
            return "{}";
        }

        StringBuilder builder = new StringBuilder(size() * 28);
        builder.append('{');
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<K, V> entry = it.next();
            K key = entry.getKey();
            V value = entry.getValue();
            builder.append(key == this ? "(this collections.map.Map)" : key);
            builder.append('=');
            builder.append(value == this ? "(this collections.map.Map)" : value);
            if(it.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append('}');
        return builder.toString();
    }

    @Override
    public Collection<V> values() {
        if(valuesCollection == null) {
            valuesCollection = new AbstractCollection<V>() {
                @Override
                public Iterator<V> iterator() {
                    return new Iterator<V>() {
                        Iterator<Map.Entry<K, V>> setIterator = entrySet().iterator();

                        @Override
                        public boolean hasNext() {
                            return setIterator.hasNext();
                        }

                        @Override
                        public V next() {
                            return setIterator.next().getValue();
                        }

                        @Override
                        public void remove() {
                            setIterator.remove();
                        }
                    };
                }

                @Override
                public int size() {
                    return AbstractMap.this.size();
                }

                @Override
                public boolean contains(Object o) {
                    return containsValue(o);
                }
            };
        }
        return valuesCollection;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AbstractMap<K, V> result = (AbstractMap<K, V>) super.clone();
        result.keySet = null;
        result.valuesCollection = null;
        return result;
    }
}
