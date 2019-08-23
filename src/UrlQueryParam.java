import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlQueryParam {
    public static void main(String[] args) {
        String url = "http://oimagea3.ydstatic.com/image?id=2025617599315045738&product=dict-homepage&cw=1024";
        int width = 570;
        String trans = url;
        String regex = "\\bw=.*?(&|$)";
        Matcher matcher = Pattern.compile(regex).matcher(url);
//        System.out.println(matcher.find());
        if (matcher.find()) {
            System.out.println("found");
            trans = matcher.replaceFirst("w=570$1");
//            trans = url.replaceFirst("\\bw=.*?(&|$)", "w=570$1");
        } else {
            System.out.println("not found");
            trans += "&w=570";
        }
        System.out.println(trans);
    }
}
