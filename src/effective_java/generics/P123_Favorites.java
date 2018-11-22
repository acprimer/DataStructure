package effective_java.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaodh on 2017/11/5.
 */
public class P123_Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        if (type == null) {
            throw new NullPointerException("Type is null");
        }
        favorites.put(type, instance);
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    public static void main(String[] args) {
        P123_Favorites f = new P123_Favorites();
        f.putFavorite(String.class, "java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, P123_Favorites.class);
        List<String> sList = new ArrayList<>();
        sList.add("hello");
        sList.add("world");
        f.putFavorite(List.class, sList);
        String fs = f.getFavorite(String.class);
        int fi = f.getFavorite(Integer.class);
        Class<?> fc = f.getFavorite(Class.class);
        List<String> fl = f.getFavorite(List.class);
        System.out.printf("%s %d %s %d\n", fs, fi, fc.getName(), fl.size());
    }
}
