package com.android.handler;

import java.time.Clock;

/**
 * Created by yaodh on 2017/2/9.
 */
public class MainThread {
    public static void main(String[] args) {
        new MainThread().start();
    }

    private void start() {
        Looper.prepare();

        onCreate();

        Looper.loop();
    }

    private void onCreate() {
        Thread mainThread = Thread.currentThread();
        System.out.println("Thread " + Thread.currentThread());

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println("Thread " + Thread.currentThread() + " " + (mainThread == Thread.currentThread() ? "true" : "false"));
                System.out.println(msg);
            }
        };

        new Thread() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                }
                System.out.println("Thread " + Thread.currentThread());
                Message message = new Message();
                message.what = 2;
                message.obj = "obj";
                handler.handleMessage(message);
            }
        }.start();
    }
}
