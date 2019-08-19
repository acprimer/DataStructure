package jvm;

import java.nio.ByteOrder;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class BigEndinness {
    public static void main(String[] args) {
        if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
            System.out.println("Big-endian");
        } else {
            System.out.println("Little-endian");
        }
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
        Thread thread = new Thread(task);
        int[] arr = new int[2];
        System.out.println(arr instanceof Object);
    }
}
