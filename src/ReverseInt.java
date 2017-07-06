/**
 * Created by yaodh on 2017/7/5.
 */
public class ReverseInt {
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("00123"));
        System.out.println(Integer.parseInt("+123"));
        System.out.println(Integer.parseInt("-123"));
        System.out.println(Integer.parseInt("123"));

        ReverseInt reverseInt = new ReverseInt();
        System.out.println(reverseInt.reverse(120));
    }

    private int reverse(int num) {
        StringBuilder str = new StringBuilder(String.valueOf(num)).reverse();
        return Integer.parseInt(str.toString());
    }
}
