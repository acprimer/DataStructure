/**
 * Created by yaodh on 2017/3/8.
 */
public class TestClassFile {

    public static void main(String[] args) {
        System.out.println(circumference(1.6f));
    }

    public static float circumference(float r) {
        float pi = 3.14f;
        float area = 2 * pi * r;
        return area;
    }
}
