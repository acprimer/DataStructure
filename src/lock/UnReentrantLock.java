package lock;

/**
 * Created by yaodh on 2018/4/6.
 */
public class UnReentrantLock {
    private boolean isLock = false;
    Thread lockedBy = null;
    int lockCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (isLock && callingThread != lockedBy) {
            wait();
        }
        isLock = true;
        lockCount++;
        lockedBy = callingThread;
    }
    public synchronized void unlock() {
        if (Thread.currentThread() == lockedBy) {
            lockCount--;
            if (lockCount == 0) {
                isLock = false;
                notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UnReentrantLock lock = new UnReentrantLock();
        lock.lock();
        System.out.println("获得锁");
//        lock.unlock();
        lock.lock();
        System.out.println("再次获得锁");
        lock.unlock();
    }
}
