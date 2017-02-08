import java.io.*;

/**
 * Created by yaodh on 2016/11/14.
 */
public class TransWiki {
    public static void main(String[] args) {
        File fileInput = new File("E:\\Youdao\\Hindict\\Log统计\\log.txt");
        File fileOutput = new File("E:\\Youdao\\Hindict\\Log统计\\logwiki.txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileInput), "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput));
            String line;
            writer.write("{|class=\"wikitable\"\n|-\n");
            while ((line = reader.readLine()) != null) {
                String[] params = line.split("\t");
                for (int i = 0; i < 10; i++) {
                    String s = (i < params.length ? params[i] : "");
                    writer.write("| " + s + "\n");
                }
                writer.write("|-\n");
            }
            writer.write("|}");
            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
