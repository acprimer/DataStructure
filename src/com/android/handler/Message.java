package com.android.handler;

/**
 * Created by yaodh on 2017/2/9.
 */
public class Message {
    Handler target;
    public Object obj;
    public int what;

    @Override
    public String toString() {
        return "Message what = " + what + " obj = " + obj.toString();
    }
}
