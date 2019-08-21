package shengsiyuan;

public class L11Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
    }
}
class ClassLoaderTest {
    static {
        System.out.println("static block");
    }
}