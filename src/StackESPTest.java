public class StackESPTest {

    public static void main(String[] args) {
        StackESPTest test = new StackESPTest();
        test.fun();
        System.out.println();
    }

    private void fun() {
        int a = 1;
        int b = 2;
        int c = a + add(a, b);
        int d = c + add(a, a, a);
    }

    private int add(int a, int b) {
        return a+b;
    }

    private int add(int a, int b, int c) {
        return a+b+c;
    }

    private void g() {
        {int a = 1;}
        int b= 1;
    }
}
