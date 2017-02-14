package com.android.handler;

/**
 * Created by yaodh on 2017/2/9.
 */
public class Message {
    static final int FLAG_IN_USE = 1 << 0;
    static final int FLAG_ASYNCHRONOUS = 1 << 1;

    public int what;
    public int arg1, arg2;
    public Object obj;
    long when;
    private int flags;

    Handler target;
    Runnable callback;

    Message next;

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

    public static Message obtain(Handler handler) {
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

    public boolean isAsynchronous() {
        return (flags & FLAG_ASYNCHRONOUS) != 0;
    }

    public void setAsynchronous(boolean async) {
        if (async) {
            flags |= FLAG_ASYNCHRONOUS;
        } else {
            flags &= ~FLAG_ASYNCHRONOUS;
        }
    }

    boolean isInUse() {
        return (flags & FLAG_IN_USE) != 0;
    }

    void markInUse() {
        flags |= FLAG_IN_USE;
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
        return "Message " + super.toString() + " what = " + what + " obj = " + (obj == null ? "null" : obj);
    }
}
