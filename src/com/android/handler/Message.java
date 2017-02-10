package com.android.handler;

/**
 * Created by yaodh on 2017/2/9.
 */
public class Message {
    static final int FLAG_IN_USE = 1 << 0;

    public int what;
    public int arg1, arg2;
    public Object obj;
    long when;
    private int flags;

    Handler target;
    Runnable callback;

    private Message next;

    private static final Object sPoolSync = new Object();
    private static Message sPool;
    private static int sPoolSize = 0;

    private static final int MAX_POOL_SIZE = 50;
    private static boolean gCheckRecycle = true;

    public Message() {
    }

    public static Message obtain() {
        synchronized (sPoolSync) {
            if(sPool != null) {
                Message m = sPool;
                sPool = m.next;
                m.next = null;
                m.flags = 0;
                sPoolSize--;
                return m;
            }
        }
        return new Message();
    }

    public static Message obtarin(Handler handler) {
        Message m = obtain();
        m.target = handler;

        return m;
    }

    public static void updateCheckRecycle(int targetSdkVersion) {
        if (targetSdkVersion < 21) {
            gCheckRecycle = false;
        }
    }

    public void recycle() {
        if (isInUse()) {
            if (gCheckRecycle) {
                throw new IllegalStateException("This message cannot be recycled because" +
                        "it is still in use");
            }
            return;
        }
        recycleUnckecked();
    }

    void recycleUnckecked() {
        flags = FLAG_IN_USE;
        what = 0;
        arg1 = 0;
        arg2 = 0;
        obj = null;

        synchronized (sPoolSync) {
            if (sPoolSize < MAX_POOL_SIZE) {
                next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }

    boolean isInUse() {
        return (flags & FLAG_IN_USE) != 0;
    }

    public Handler getTarget() {
        return target;
    }

    public void setTarget(Handler target) {
        this.target = target;
    }

    public Runnable getCallback() {
        return callback;
    }

    @Override
    public String toString() {
        return "Message what = " + what + " obj = " + obj.toString();
    }
}
