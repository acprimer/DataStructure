import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlRegular {
    public static void main(String[] args) {
        getTouchSpanInternal("<i>good test</i><br>\n\n</br>hello test-int", 0);
    }

    public static CharSequence getTouchSpanInternal(CharSequence text, int normalTextColor) {
        // 英文字母大小写和连词符-
        Pattern pattern = Pattern.compile("[a-zA-Z\\-\\n</>]+");
        Matcher matcher = pattern.matcher(text);
        StringBuilder spanString = new StringBuilder(text);

        int consumed = 0;
        int index = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            System.out.println(text.subSequence(start, end));
//            if (isTagStart(text, "<i>", start)) {
//                while (!isTagEnd(text, "</i>", end)) {
//                    end = matcher.end();
//                }
//                start -= consumed;
//                end -= consumed;
//                spanString.delete(end, end + 4);
//                spanString.delete(start - 3, start);
//                consumed+=7;
//                continue;
//            }
//            if (isTagStart(text, "<b>", start)) {
//                while (!isTagEnd(text, "</b>", end)) {
//                    end = matcher.end();
//                }
//                start -= consumed;
//                end -= consumed;
//                spanString.delete(end, end + 4);
//                spanString.delete(start - 3, start);
//                consumed+=7;
//                continue;
//            }
        }
        return spanString;
    }

    private static boolean isTagStart(CharSequence text, String tag, int start) {
        int len = tag.length();
        if (start - len >= 0 && text.subSequence(start - len, start).equals(tag)) {
            return true;
        }
        return false;
    }

    private static boolean isTagEnd(CharSequence text, String tag, int end) {
        int len = tag.length();
        if (end + len < text.length() && text.subSequence(end, end + len).equals(tag)) {
            return true;
        }
        return false;
    }
}
