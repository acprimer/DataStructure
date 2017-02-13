package com.android.handler;

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

//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Looper.myLooper().quit();
    }

    private void onCreate() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println(msg);
            }
        };

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                Message message = new Message();
                message.what = 2;
                message.obj = "obj";
                handler.sendMessage(message);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                message = Message.obtain();
                message.what = 3;
                message.obj = "haha";
                int token = handler.mQueue.postSyncBarrier();
                handler.sendMessage(message);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                handler.mQueue.removeSyncBarrier(token);

            }
        }.start();
    }
}
