package com.android.handler;

/**
 * Created by yaodh on 2017/7/6.
 */
public class ReceiverThreadA extends Thread {
    private Handler mHandler;

    @Override
    public void run() {
        // create a looper in non-ui thread
        Looper.prepare();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String line = msg.toString();
                if (line.equalsIgnoreCase("q")) {
                    exit();
                }
                Log.d("I receive a message from ThreadB " + msg);
            }
        };
        Looper.loop();
    }

    public Handler getHandler() {
        return mHandler;
    }

    public void exit() {
        Looper.myLooper().quit();
        interrupt();
    }
}
