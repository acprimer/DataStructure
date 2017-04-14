package collections.map;

import java.util.Collection;
import java.util.Set;

/**
 * Created by yaodh on 2016/10/10.
 */
public interface Map<K, V> {
    interface Entry<K, V> {
        boolean equals(Object object);
        K getKey();
        V getValue();
        int hashCode();
        V setValue(V value);
    }

    void clear();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    Set<Entry<K, V>> entrySet();
    boolean equals(Object object);
    V get(Object key);
    int hashCode();
    boolean isEmpty();
    Set<K> keySet();
    V put(K key, V value);
    void putAll(Map<? extends K, ? extends V> map);
    V remove(Object key);
    int size();
    Collection<V> values();
}
