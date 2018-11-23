import java.util.Scanner;

/**
 * Created by yaodh on 2018/9/11.
 */
public class TransLanguage {
    public static void main(String[] args) {
//        String[] tts = {"ar","cs","da","de",
//                "el","es","fi","fr","he","hi","hu","id","it",
//                "nl","no","pl","pt","ro","ru","sk","sv","th","tr","chn","hk","en","ja","ko"};
//        Arrays.sort(tts);
//        for (int i = 0; i < tts.length; i++) {
//            System.out.print(String.format("\"%s\",", tts[i]));
//        }
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while (sc.hasNext()) {
//            String line = sc.nextLine().split("\"")[1].toUpperCase();
//            System.out.println("int " + line + " = " + n + ";");
            String line = sc.nextLine();
//            System.out.println(String.format("<item>%s</item>", line));
            System.out.println(line.split(":")[4].trim());
            n++;
        }
    }

}
