package com.android.handler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yaodh on 2017/2/9.
 */
public class MessageQueue {
    private BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();

    public Message next() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    void enqueueMessage(Message message) {
        try {
            blockingQueue.put(message);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
