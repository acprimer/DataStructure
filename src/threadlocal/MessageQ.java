package threadlocal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaodh on 2017/8/13.
 */
public class MessageQ {
    private static final ThreadLocal<MessageQ> sThreadLocal = new ThreadLocal<MessageQ>();
    private List<String> data = new ArrayList<>();

    private static MessageQ myQueue() {
        if (sThreadLocal.get() == null) {
            sThreadLocal.set(new MessageQ());
        }
        return sThreadLocal.get();
    }

    public static void put(String e) {
        MessageQ queue = myQueue();
        queue.data.add(e);
    }

    public static int size() {
        return myQueue().data.size();
    }

    public static String get(int position) {
        return myQueue().data.get(position);
    }
}
