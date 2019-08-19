package patterns.proxy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StaticProxy {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map = Collections.synchronizedMap(map);
    }
}
