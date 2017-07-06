package com.android.handler;

/**
 * Created by yaodh on 2017/7/6.
 */
public class TestCommunicate {
    public static void main(String[] args) {
        Log.d("Test thread communicate...");
        ReceiverThreadA receiver = new ReceiverThreadA();
        receiver.start();
        // sleep 0.5s to ensure the handler not null
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        // print looper & queue
        Handler cHandler = receiver.getHandler();
        Log.d("The Looper in the communicate thread " + cHandler.mLooper);
        Log.d("The MessageQueue in the communicate thread " + cHandler.mQueue);
        SenderThreadB sender = new SenderThreadB(cHandler);
        sender.start();
    }
}
