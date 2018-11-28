package jvm;

import java.nio.ByteOrder;

public class BigEndinness {
    public static void main(String[] args) {
        if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
            System.out.println("Big-endian");
        } else {
            System.out.println("Little-endian");
        }
    }
}
