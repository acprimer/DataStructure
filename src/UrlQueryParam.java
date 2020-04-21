import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlQueryParam {
    public static void main(String[] args) {
        String url = "http://oimagec3.ydstatic.com/image?id=-4807299960218977578&product=dict-homepage&of=jpeg";
//        int width = 570;
//        String trans = url;
//        String regex = "\\bw=.*?(&|$)";
//        Matcher matcher = Pattern.compile(regex).matcher(url);
////        System.out.println(matcher.find());
//        if (matcher.find()) {
//            System.out.println("found");
//            trans = matcher.replaceFirst("w=570$1");
////            trans = url.replaceFirst("\\bw=.*?(&|$)", "w=570$1");
//        } else {
//            System.out.println("not found");
//            trans += "&w=570";
//        }
//        System.out.println(trans);

        String a = replaceOrAppendParam(url, "w", 570);
        System.out.println(a);
        a = replaceOrAppendParam(a, "of", "webp");
        System.out.println(a);

        String vv = "a=3&b=5";
        System.out.println(readStringValue(vv, "a"));
        System.out.println(readStringValue(vv, "b"));
    }

    private static String replaceOrAppendParam(String url, String key, Object value) {
        String regex = String.format("\\b%s=.*?(&|$)", key);
        Matcher matcher = Pattern.compile(regex).matcher(url);
        if (matcher.find()) {
            return matcher.replaceFirst(key + "=" + value.toString() + "$1");
        } else {
            return String.format(Locale.US, "%s&%s=%s", url, key, value.toString());
        }
    }

    private static String readStringValue(String str, String key) {
        String regex = String.format("\\b%s=.*?(&|$)", key);
        Matcher matcher = Pattern.compile(regex).matcher(str);
        if (matcher.find()) {
            return str.substring(matcher.start(), matcher.end());
        } else {
            return "not found";
        }
    }
}
