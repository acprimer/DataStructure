package com.android.handler;

/**
 * Created by yaodh on 2017/2/9.
 */
public class Handler {
    private MessageQueue messageQueue;

    public Handler() {
        Looper looper = Looper.myLooper();

        if(looper == null) {
            throw new RuntimeException("Can't create handler inside thread.");
        }

        this.messageQueue = looper.messageQueue;
    }

    public void sendMessage(Message msg) {
        msg.target = this;
        messageQueue.enqueueMessage(msg);
    }

    public void handleMessage(Message msg) {

    }
}
