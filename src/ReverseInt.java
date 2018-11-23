import collections.map.HashMap;

/**
 * Created by yaodh on 2017/7/5.
 */
public class ReverseInt {
    public static void main(String[] args) {
//        System.out.println(Integer.parseInt("00123"));
//        System.out.println(Integer.parseInt("+123"));
//        System.out.println(Integer.parseInt("-123"));
//        System.out.println(Integer.parseInt("123"));
//
        ReverseInt reverseInt = new ReverseInt();
//        System.out.println(reverseInt.reverse2(120));
//        System.out.println(reverseInt.reverse2(123));
//        System.out.println(reverseInt.reverse2(-111));
//        System.out.println(reverseInt.reverse2(0));
//        System.out.println(reverseInt.reverse2(-120));
//        System.out.println(reverseInt.reverse2(-123));
//        System.out.println(reverseInt.reverse3(2147483647));
//        System.out.println(reverseInt.reverse3(21));

        System.out.println("------");
//        System.out.println(reverseInt.reverse4(-221));
        System.out.println(10000 == new Integer(10000));
//        Pair pair = new Pair(1, 2);
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

    private int reverse3(int num) throws RuntimeException {
        int ans = 0;
        while (num != 0) {
            if (ans > Integer.MAX_VALUE / 10) {
                throw new RuntimeException("number overflow");
            }
            ans = ans * 10 + num % 10;
            num /= 10;
        }
        return ans;
    }

    private int reverse4(int x) {
        String str = ""+ x;
        char[] clist = str.toCharArray();
        String res = "";
        for(int i = clist.length - 1; i >=0 ; i-- ){
            res += clist[i];
        }
        int resint = Integer.parseInt(res);
        return resint;
    }
}
