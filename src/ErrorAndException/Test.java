package ErrorAndException;

/**
 * Created by yaodh on 2018/3/21.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println(take());
    }
    public static int take() throws Exception{
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            System.out.println("throw");
            throw e;
        } finally {
            return 1;
        }
    }
}
