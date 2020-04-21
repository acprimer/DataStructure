package shengsiyuan;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

public class TestFinal {
    public static void main(String[] args) {
        try {
            InetAddress[] name = InetAddress.getAllByName("http://www.youdao.com/");
            for (InetAddress a : name) {
                System.out.println(a.getHostAddress());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ObjectFinalField obj = new ObjectFinalField();
//        System.out.println(obj);
//        reflectUpdateField(obj, "a", 4);
//        reflectUpdateField(obj, "b", 5);
//        reflectUpdateField(obj, "c", 6);
//        reflectUpdateField(obj, "d", new Object());
//        reflectUpdateField(obj, "e", new Object());
//        reflectUpdateField(obj, "f", "abc");
//        reflectUpdateField(obj, "g", "def");
//        System.out.println(obj);

//        char ch = '\u0901';
//        while (ch <= '\u097F') {
//            System.out.print(ch++);
//        }
//
//        System.out.println();

        System.out.println('\u21c4');
        System.out.println('\u032e');
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

    private static void test() throws IOException {

    }
}
