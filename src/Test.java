/**
 * Created by yaodh on 2016/10/10.
 */

//import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>(4);
        map.put("a", 0);
        map.put("sf", "sfda");
        map.put("b", "c");
        map.put("c", 3.0);
        map.put("d", map);
        map.put("n", "sl");
        map.put("va", "sfd");
        map.put(null, "sl");
        System.out.println(map);
    }
}
