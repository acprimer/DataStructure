package jvm;

public class LightWeightLockTest {
    public static void main(String[] args) {
        MemoryLayoutTest obj = new MemoryLayoutTest();
        obj.printHash();
        obj.print();
        obj.printSync();
        obj.print();
    }
}
