package com.android.handler;

import java.util.Scanner;

/**
 * Created by yaodh on 2017/7/6.
 */
public class SenderThreadB extends Thread {
    private Handler mHandler;

    public SenderThreadB(Handler handler) {
        this.mHandler = handler;
    }

    @Override
    public void run() {
        if (mHandler == null) {
            throw new IllegalArgumentException("Handler must not be null");
        }

        Scanner sc = new Scanner(System.in);
        String line = null;
        System.out.println("input your message");
        while (sc.hasNext()) {
            line = sc.nextLine();
            Message msg = Message.obtain();
            msg.obj = line;
            mHandler.sendMessage(msg);
            if (line.equalsIgnoreCase("q")) {
                break;
            }
            System.out.println("input your message");
        }
        Log.d("Thread B is closed.");
    }
}
