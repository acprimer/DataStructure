package interview;

/**
 * Created by yaodh on 2017/7/27.
 * 输入：一个带转义字符’\b’的字符串
 * 要求：删除转义字符’\b’和它前面的字符，如果遇到多个连续的’\b’，则删除相同数量的转义字符和前面的字符
 * 输出：最终的结果字符串
 * 举例：输入"abc\b\bd\b\bghi"，期望输出"ghi"
 * 链接：https://mp.weixin.qq.com/s/jfOepXPZC15DuMmkAdYicA
 */
public class TypingDelete {

    public static String parse(String line) {
        if (line == null) {
            return "";
        }
        int len = line.length();
        char[] stack = new char[len];
        int top = 0;
        for (int i = 0; i < len; i++) {
            char ch = line.charAt(i);
            if (ch == '\b') {
                // encounter '\b'
                if (top > 0) top--;
            } else {
                // other characters
                stack[top++] = ch;
            }
        }
        return new String(stack, 0 , top);
    }

    public static void main(String[] args) {
        System.out.println(String.format("%3.1f", 3.1));
        System.out.println(String.format("%.0f", 23.1));
        System.out.println("20170728".substring(6, 8));
        System.out.println(parse("abc\b\bd\b\bghi"));
        System.out.println(parse("abc\b\bd\b\bghi\b\b\b\b\b"));
        System.out.println(parse("abcd"));
    }
}
