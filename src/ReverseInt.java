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
        System.out.println(reverseInt.reverse2(120));
        System.out.println(reverseInt.reverse2(123));
        System.out.println(reverseInt.reverse2(-111));
        System.out.println(reverseInt.reverse2(0));
        System.out.println(reverseInt.reverse2(-120));
        System.out.println(reverseInt.reverse2(-123));
    }

    private int reverse1(int num) {
        StringBuilder str = new StringBuilder(String.valueOf(num)).reverse();
        return Integer.parseInt(str.toString());
    }

    private int reverse2(int num) {
        int ans = 0;
        while (num != 0) {
            ans = ans * 10 + num % 10;
            num /= 10;
        }
        return ans;
    }
}
