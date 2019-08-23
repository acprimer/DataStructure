package learn_enum;

/**
 * Created by yaodh on 2018/7/6.
 */
public class EnumTest {
    enum Ad {
        Youdao, Facebook
    }

    public static void main(String[] args) {
        System.out.println(Ad.Youdao.ordinal());
        System.out.println(Ad.Facebook.ordinal());

    }
}
