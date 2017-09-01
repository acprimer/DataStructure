package date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by yaodh on 2017/8/9.
 */
public class DataOperation {
    public static void main(String[] args) {
//        getDays(1502653004516L);
//        getDays(1502656922978L);
//        getDays(1502732523877L);
//        getDays(1502733374828L);
//        getDays(1502817252216L);
//        getDays(1502355438691L);
//
//        getDays(1502355675723L);
//        getDays(1502398868931L);

        printDay(1502355675723L);
        printDay(1502398868931L);
        printDay(1502380800000L);

        System.out.println(new Random().nextInt(5));
        System.out.println(new Random().nextInt(5));
        System.out.println(new Random().nextInt(5));
        System.out.println(new Random().nextInt(5));

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.subList(1, list.size()));

        String[] as = new String[] {"a", "b"};
        System.out.println(Arrays.toString(as));

        System.out.println(cmpVersion("1.0", "1.1"));
    }

    private static void getDays(long time) {
        long days = TimeUnit.MILLISECONDS.toDays(time);
        System.out.println(days);
    }

    private static void printDay(long time) {
        double ad = (1.0 * time / 1000 / 60 / 60 + 8) / 24;
        System.out.println(String.format("%.2f", ad));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(time));
        System.out.println();
    }

    public static int cmpVersion(String source, String target) {
        String[] sArr = source.split("\\.");
        String[] tArr = target.split("\\.");
        for (int i = 0; i < sArr.length; i++) {
            int si = Integer.parseInt(sArr[i]);
            int ti = Integer.parseInt(tArr[i]);
            if (si != ti) {
                return si - ti;
            }
        }
        return 0;
    }
}
