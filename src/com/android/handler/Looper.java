package com.android.handler;

/**
 * Created by yaodh on 2017/2/9.
 */
public class Looper {
    private static final ThreadLocal<Looper> threadLocal = new ThreadLocal<>();
    final MessageQueue messageQueue;

    private Looper() {
        messageQueue = new MessageQueue();
    }

    public static void prepare() {
        if(threadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        threadLocal.set(new Looper());
    }

    public static void loop() {
        Looper looper = myLooper();
        if(looper == null) {
            throw new RuntimeException("No looper.");
        }
        MessageQueue messageQueue = looper.messageQueue;
        for(;;) {
            Message message = messageQueue.next();
            message.target.handleMessage(message);
        }
    }

    public static Looper myLooper() {
        return threadLocal.get();
    }
}
