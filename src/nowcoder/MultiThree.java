package nowcoder;

import java.util.Scanner;

/**
 * Created by yaodh on 2018/4/8.
 */
public class MultiThree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        System.out.println(fun(y) - fun(x - 1));
    }

    private static int fun(int x) {
        return (x / 3 * 2) + (x % 3 > 0 ? (x % 3 - 1) : 0);
    }
}
