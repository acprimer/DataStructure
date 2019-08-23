package shengsiyuan;

import jvm.ObjectA;

import java.lang.reflect.Field;

public class TestFinal {
    public static void main(String[] args) {
        ObjectFinalField obj = new ObjectFinalField();
        System.out.println(obj);
        reflectUpdateField(obj, "a", 4);
        reflectUpdateField(obj, "b", 5);
        reflectUpdateField(obj, "c", 6);
        reflectUpdateField(obj, "d", new Object());
        reflectUpdateField(obj, "e", new Object());
        reflectUpdateField(obj, "f", "abc");
        reflectUpdateField(obj, "g", "def");
        System.out.println(obj);
    }

    private static void reflectUpdateField(Object obj, String field, Object value) {
        try {
            Field fi3 = obj.getClass().getDeclaredField(field);
            fi3.setAccessible(true);
            fi3.set(obj, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
