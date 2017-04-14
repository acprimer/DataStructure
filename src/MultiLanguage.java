import collections.map.HashMap;
import collections.map.Map;

import java.io.*;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by yaodh on 2017/2/24.
 */
public class MultiLanguage {

    private static void getAllLanguage() {
        getLanguage("strings.xml");
        getLanguage("strings-es.xml");
        getLanguage("strings-id.xml");
        getLanguage("strings-pt.xml");
        getLanguage("strings-th.xml");
        getLanguage("strings-vi.xml");
    }

    public static void getLanguageArray() {
        Arrays.sort(LANGUAGE_CODE);
        File file = new File("E:\\Youdao\\Hindict\\国际版\\语言文案\\谷歌翻译的文案\\strings.xml");
        System.out.println("\n\n\n" + file.getAbsolutePath() + "\n\n\n");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            int id = 0;
            while ((line = reader.readLine()) != null) {
                int id1 = line.indexOf("\"");
                int id2 = line.indexOf("\"", id1 + 1);
                if (id1 > 0 && id2 > 0) {
                    if (line.substring(id1 + 1, id2).equals("twslang_" + LANGUAGE_CODE[id])) {
//                        System.out.println(line);
                        String out = String.format("<item>@string/twslang_%s</item>", LANGUAGE_CODE[id]);
                        System.out.println(out);
                        id++;
                    }
                    if (id >= LANGUAGE_CODE.length) {
                        return;
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getLanguage(String source) {
        Arrays.sort(LANGUAGE_CODE);
        File file = new File("E:\\Youdao\\Hindict\\国际版\\语言文案\\谷歌翻译的文案\\" + source);
        System.out.println("\n\n\n" + file.getAbsolutePath() + "\n\n\n");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            int id = 0;
            while ((line = reader.readLine()) != null) {
                int id1 = line.indexOf("\"");
                int id2 = line.indexOf("\"", id1 + 1);
                if (id1 > 0 && id2 > 0) {
                    if (line.substring(id1 + 1, id2).equals("twslang_" + LANGUAGE_CODE[id])) {
                        System.out.println(line);
                        id++;
                    }
                    if (id >= LANGUAGE_CODE.length) {
                        return;
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test() {
        File file = new File("E:\\Youdao\\Hindict\\国际版\\语言文案\\list2.txt");
        File fileInput = new File("E:\\AndroidProject\\hindict_android\\Hindict\\src\\main\\res\\values\\strings.xml");
        File[] output = new File[]{
//                new File("E:\\Youdao\\Hindict\\国际版\\语言文案\\strings - th.xml"),
//                new File("E:\\Youdao\\Hindict\\国际版\\语言文案\\strings - vi.xml"),
//                new File("E:\\Youdao\\Hindict\\国际版\\语言文案\\strings - pu.xml"),
                new File("E:\\Youdao\\Hindict\\国际版\\语言文案\\strings - es.xml"),
                new File("E:\\Youdao\\Hindict\\国际版\\语言文案\\strings - id.xml"),
        };
        Map<String, String[]> map = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            BufferedWriter[] writers = new BufferedWriter[output.length];
            for (int i = 0; i < output.length; i++) {
                writers[i] = new BufferedWriter(new FileWriter(output[i]));
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] params = line.split("\t");
                map.put(params[0], params);
            }
            reader.close();

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileInput), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                int id1 = line.indexOf('>');
                int id2 = line.indexOf('<', id1);
                if (id1 < 0 || id2 < 0) {
                    for (int i = 0; i < writers.length; i++) {
//                        writers[i].write(line + "\n");
//                        System.out.println(line);
                    }
                    continue;
                }
                String content = line.substring(id1 + 1, id2);
                if (map.containsKey(content)) {
                    for (int i = 0; i < writers.length; i++) {
                        writers[i].write(line.substring(0, id1 + 1) + map.get(content)[i + 1] + line.substring(id2) + "\n");
                        System.out.println(line.substring(0, id1 + 1) + map.get(content)[i + 1] + line.substring(id2));
                    }
                } else {
//                    System.out.println(content);
//                    for (int i = 0; i < writers.length; i++) {
//                        writers[i].write(line + "\n");
//                    }
                }
            }
            reader.close();
            for (int i = 0; i < writers.length; i++) {
                writers[i].close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getSortedCode(String sorted) {
        String[] split = sorted.split(":");
        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < LANGUAGE_CODE.length; j++) {
                if (LANGUAGE_CODE[j].equals(split[i])) {
                    System.out.print(LANGUAGE_CODE[j] + ":");
                }
            }
        }
        System.out.println();
    }

    private static void getSortedCode() {
        String[] origin = new String[]{
                // en
                "auto:af:sq:am:ar:hy:az:eu:be:bn:bs:bg:ca:ceb:ny:zh:zh-CN:zh-TW:co:hr:cs:da:nl:en:eo:et:tl:fi:fr:fy:gl:ka:de:el:gu:ht:ha:haw:iw:hi:hmn:hu:is:ig:id:ga:it:ja:jw:kn:kk:km:ko:ku:ky:lo:la:lv:lt:lb:mk:mg:ms:ml:mt:mi:mr:mn:my:ne:no:ps:fa:pl:pt:pa:ro:ru:sm:gd:sr:st:sn:sd:si:sk:sl:so:es:su:sw:sv:tg:ta:te:th:tr:uk:ur:uz:vi:cy:xh:yi:yo:zu",
                // es
                "auto:af:sq:de:am:ar:hy:az:bn:be:my:bs:bg:kn:ca:ceb:cs:ny:zh:zh-CN:zh-TW:si:ko:co:ht:hr:da:sk:sl:es:eo:et:eu:fi:fr:fy:gd:cy:gl:ka:el:gu:ha:haw:iw:hi:hmn:nl:hu:ig:id:en:ga:is:it:ja:jw:km:kk:ky:ku:lo:la:lv:lt:lb:mk:ml:ms:mg:mt:mi:mr:mn:ne:no:ps:fa:pl:pt:pa:ro:ru:sm:sr:st:sn:sd:so:sw:sv:su:tl:th:ta:tg:te:tr:uk:ur:uz:vi:xh:yi:yo:zu",
                // id
                "auto:af:sq:am:ar:hy:az:haw:id:eu:nl:bn:bs:bg:my:be:ceb:cs:ny:zh:zh-CN:zh-TW:da:eo:et:fa:tl:fy:gd:gl:ka:gu:ha:hi:hmn:iw:ig:en:ga:is:it:jw:ja:de:kn:ca:kk:km:ky:ko:co:ht:hr:ku:lo:la:lv:lt:lb:hu:mk:mg:ml:mt:mi:mr:ms:mn:ne:no:ps:pl:pt:fr:pa:ro:ru:sm:sr:st:sn:sd:si:sl:sk:so:es:su:fi:sw:sv:tg:ta:te:th:tr:uk:ur:uz:vi:cy:xh:yi:yo:el:zu",
                // pt
                "auto:af:sq:de:am:ar:hy:az:eu:bn:be:my:bs:bg:ca:kk:ceb:ny:zh:zh-CN:zh-TW:si:ko:co:ht:hr:da:sk:sl:es:eo:et:fi:fr:fy:gl:cy:gd:ka:el:gu:ha:haw:iw:hi:hmn:nl:hu:ig:id:en:yo:ga:is:it:ja:jw:kn:km:ku:lo:la:lv:lt:lb:mk:ml:ms:mg:mt:mi:mr:mn:ne:no:ps:fa:pl:pt:pa:ky:ro:ru:sm:sr:st:sn:sd:so:sw:su:sv:tg:tl:th:ta:cs:te:tr:uk:ur:uz:vi:xh:yi:zu",
                // th
                "auto:ny:sm:my:el:kn:gl:gu:gd:ko:km:co:ca:kk:ky:ku:hr:ka:zh:zh-TW:zh-CN:jw:cs:sn:ceb:su:zu:st:sr:xh:so:ja:nl:da:tr:te:ta:tg:th:no:ne:bs:bg:bn:be:eu:pa:fa:pt:pl:fr:ps:fy:fi:tl:hmn:mr:mn:mt:mk:ms:mi:ml:yi:uk:de:yo:ru:ro:la:lv:lt:cy:vi:es:sk:sl:sw:sv:sd:en:am:sq:az:hy:ar:ig:it:id:uz:ur:et:eo:af:is:ga:hu:ha:haw:hi:iw:ht:mg:lb:lo:si",
                // vi
                "auto:eo:ar:az:ga:is:am:sq:en:hy:pl:fa:xh:sw:eu:bn:be:bs:pt:bg:ca:ceb:ny:co:ht:hr:da:iw:kn:de:et:fy:gl:ka:gu:nl:ko:ha:haw:hi:hmn:hu:el:ig:id:jw:kk:km:ku:ky:lo:la:lv:lt:lb:ms:mk:mg:ml:mt:mi:mr:my:mn:no:af:ne:ru:ja:ps:fr:fi:tl:pa:ro:sm:cs:st:sn:sd:si:sk:sl:so:tg:ta:es:te:th:tr:sv:zh:zh-CN:zh-TW:uk:ur:uz:vi:sr:gd:su:cy:it:yi:yo:zu",
        };
        for (int i = 0; i < origin.length; i++) {
            getSortedCode(origin[i]);
        }
    }

    private static void getGuideIndex() {
        String tapToTranslate = "Tradução rápida";
        String translate = "rápida";
        int start = tapToTranslate.indexOf(translate);
        int end = tapToTranslate.indexOf(translate) + translate.length();
        System.out.println(start + " " + end);
    }

    public static void main(String[] args) {
        test();
    }

    private static String[] LANGUAGE_CODE = new String[]{
            "ar",
            "bn",
            "de",
            "en",
            "es",
            "fr",
            "gu",
            "hi",
            "id",
            "it",
            "ja",
            "kn",
            "ko",
            "ml",
            "mr",
            "ms",
            "ne",
            "pa",
            "ta",
            "te",
            "th",
            "ur",
            "vi",
    };

    private static void getStrings() {
        File file = new File("E:\\Youdao\\Hindict\\国际版\\语言文案\\list3.txt");
        File[] output = new File[]{
                new File("E:\\AndroidProject\\hindict_android\\Hindict\\src\\main\\res\\values\\strings.xml"),
                new File("E:\\AndroidProject\\hindict_android\\Hindict\\src\\main\\res\\values-es\\strings.xml"),
                new File("E:\\AndroidProject\\hindict_android\\Hindict\\src\\main\\res\\values-in\\strings.xml"),
                new File("E:\\AndroidProject\\hindict_android\\Hindict\\src\\main\\res\\values-pt\\strings.xml"),
                new File("E:\\AndroidProject\\hindict_android\\Hindict\\src\\main\\res\\values-th\\strings.xml"),
                new File("E:\\AndroidProject\\hindict_android\\Hindict\\src\\main\\res\\values-vi\\strings.xml"),
        };
        TreeMap<String, String[]> map = new TreeMap<>();
        try {
            BufferedWriter reader = new BufferedWriter(new FileWriter(file));
            BufferedReader[] writers = new BufferedReader[6];

            for (int i = 0; i < writers.length; i++) {
                writers[i] = new BufferedReader(new InputStreamReader(new FileInputStream(output[i])));
                String line;
                while ((line = writers[i].readLine()) != null) {
                    int id1 = line.indexOf('\"');
                    int id2 = line.indexOf('\"', id1 + 1);
                    if (id1 < 0 || id2 < 0) continue;
                    String key = line.substring(id1+1, id2);
                    id1 = line.indexOf('>');
                    id2 = line.indexOf('<', id2 + 1);
                    if (id1 < 0 || id2 < 0) continue;
                    String value = line.substring(id1+1, id2);
                    if (!map.containsKey(key)) {
                        String[] values = new String[writers.length];
                        map.put(key, values);
                    }
                    map.get(key)[i] = value;
                }
                writers[i].close();
            }

            for (String key : map.keySet()) {
                String[] arr = map.get(key);
                reader.write(key + "\t");
                for (String v : arr) {
                    reader.write(v + "\t");
                }
                reader.write("\n");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
