import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaodh on 2016/11/16.
 */

public class ListUtils {

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("hello");
        arr.add("world");
        System.out.println(join(",", null));
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 获取List的字符串
     * @param sep 分隔符
     * @param list 数据
     * @return 用分隔符分隔的字符串
     */
    public static String join(CharSequence sep, List<?> list) {
        StringBuilder builder = new StringBuilder();
        if (!isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i));
                if (i < list.size() - 1) {
                    builder.append(sep);
                }
            }
        }
        return builder.toString();
    }
}
