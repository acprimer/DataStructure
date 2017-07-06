package com.android.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yaodh on 2017/7/6.
 */
public class Log {
    public static void d(String text) {
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd-HH:mm:ss");
        Date date = new Date(currentTime);
        System.out.print(formatter.format(date) + "\t");
        System.out.println(text);
    }
}
