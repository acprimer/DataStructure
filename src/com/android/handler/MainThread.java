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
    }

    private void onCreate() {
        System.out.println("I'm the main thread " + Thread.currentThread().getId());
        Handler.Callback callback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                System.out.println("callback handleMessage " + msg);
                return false;
            }
        };
        final Handler handler = new Handler(callback) {
            @Override
            public void handleMessage(Message msg) {
                Log.d("handleMessage " + msg);
                switch (msg.what) {
                    case DownloadThread.MSG_DOWNLOAD_COMPLETE_CODE:
                        Log.d("I'm the main thread " + Thread.currentThread().getId());
                        Log.d("I got a message which said download complete.");
                        break;
                }
            }
        };

        // print looper & queue
        Log.d("The Looper in the main thread " + handler.mLooper);
        Log.d("The MessageQueue in the main thread " + handler.mQueue);

        new Thread() {
            @Override
            public void run() {
                System.out.println("I'm in run. " + Thread.currentThread().getId());
                Message message = Message.obtain();
                message.what = 2;
                message.obj = "obj";
                handler.sendMessage(message);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("I'm in runnable. " + Thread.currentThread().getId());
                    }
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                message = Message.obtain();
                message.what = 3;
                message.obj = "haha";
                System.out.println(message + " " + message.isInUse());

                int token = handler.mQueue.postSyncBarrier();
                handler.sendMessage(message);


                message = Message.obtain();
//                message.setAsynchronous(true);
                message.what = 4;
                message.obj = "test";
                handler.sendMessage(message);
                Log.d("send test message");

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                }
//                Log.d("removed");
//                handler.mQueue.removeSyncBarrier(token);

            }
        }.start();

        new DownloadThread(handler).start();
    }
}
