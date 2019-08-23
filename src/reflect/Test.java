package reflect;

import sun.reflect.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Test {
    public static <T> T newInstance(Class<T> clazz) {
        try {
            Constructor<T> cons = clazz.getConstructor();
            return cons.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        Test test = newInstance(Test.class);
//        System.out.println(test);

//        Class<?> callerClass = Reflection.getCallerClass();
//        System.out.println(callerClass);
//        System.out.println(stringToUnicode("से̮ड्"));

        String key = "wvChwnriEHwo1LbBmgsJUw==";
        byte[] barray = key.toLowerCase().getBytes();
        int m = 0;
        for (int i = key.length() - 1; i >= 0; i--) {
            if (Character.isUpperCase(key.charAt(i))) {
                m = (m << 1) + 1;
            } else {
                m = (m << 1);
            }
        }
        System.out.println(m);
        System.out.println(Arrays.toString(barray));

        int map = 1614596;
        byte[] next = new byte[]{119, 118, 99, 104, 119, 110, 114, 105, 101, 104, 119, 111, 49, 108, 98, 98, 109, 103, 115, 106, 117, 119, 61, 61};
        System.out.println(Arrays.toString(next));
        for (int i = 0; i < next.length; i++) {
            if ((map & (1<<i)) != 0) {
                next[i] -= 32;
            }
        }
        System.out.println(Arrays.toString(next));
        String key2 = new String(next);
        System.out.println(key2);
    }

    public static String stringToUnicode(String str) {
        StringBuffer sb = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            sb.append("\\u" + Integer.toHexString(c[i]));
        }
        return sb.toString();
    }
}
