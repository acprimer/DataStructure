package com.android.handler;

/**
 * Created by yaodh on 2017/7/6.
 */
public class DownloadThread extends Thread {
    public static final int MSG_DOWNLOAD_COMPLETE_CODE = 6;
    private Handler mHandler;

    public DownloadThread(Handler handler) {
        this.mHandler = handler;
    }

    @Override
    public void run() {
        Log.d("download start.");
        // sleep 1s to simulate download
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("download complete.");
        Message msg = Message.obtain();
        msg.what = MSG_DOWNLOAD_COMPLETE_CODE;
        mHandler.sendMessage(msg);

        // print looper & queue
        Log.d("The Looper in download thread " + mHandler.mLooper);
        Log.d("The MessageQueue in download thread " + mHandler.mQueue);
    }
}
