package com.android.handler;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yaodh on 2017/2/9.
 */
public class MessageQueue {
    private final boolean mQuitAllowed;
    private long mPtr;

    Message mMessages;
    private final ArrayList<IdleHandler> mIdleHandlers = new ArrayList<>();
    private IdleHandler[] mPendingIdleHandlers;
    private boolean mQuitting;
    private boolean mBlocked;
    private int mNextBarrierToken;

    private BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();

    // native method
    private static long nativeInit() {
        return 1;
    }

    private static void nativeDestroy(long ptr){

    }

    private void nativePollOnce(long ptr, int timeoutMillis){

    }

    private static void nativeWake(long ptr) {

    }

    private native static boolean nativeIsPolling(long ptr);

    private static void nativeSetFileDescriptorEvents(long ptr, int fd, int events) {

    }

    MessageQueue(boolean quitAllowed) {
        mQuitAllowed = quitAllowed;
        mPtr = nativeInit();
    }

    protected void finalize() throws Throwable {
        try {
            dispose();
        } finally {
            super.finalize();
        }
    }

    private void dispose() {
        if (mPtr != 0) {
            nativeDestroy(mPtr);
            mPtr = 0;
        }
    }

    public boolean isIdle() {
        synchronized (this) {
            final long now = System.currentTimeMillis();
            return mMessages == null || now < mMessages.when;
        }
    }

    public void addIdleHandler(IdleHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Can't add a null IdleHander");
        }
        synchronized (this) {
            mIdleHandlers.add(handler);
        }
    }

    public void removeIdleHandler(IdleHandler handler) {
        synchronized (this) {
            mIdleHandlers.remove(handler);
        }
    }

    public boolean isPolling() {
        synchronized (this) {
            return isPollingLocked();
        }
    }

    private boolean isPollingLocked() {
        return !mQuitting && nativeIsPolling(mPtr);
    }

    // 消息出队
    Message next() {
        final long ptr = mPtr;
        if (ptr == 0) {
            return null;
        }

        int pendingIdleHandlerCount = -1;
        int nextPollTimeoutMillis = 0;
        for (;;) {
            nativePollOnce(ptr, nextPollTimeoutMillis);

            synchronized (this) {
                final long now = System.currentTimeMillis();
                Message prevMsg = null;
                Message msg = mMessages;
                if (msg != null && msg.target == null) {
                    do {
                        prevMsg = msg;
                        msg = msg.next;
                    } while (msg != null && !msg.isAsynchronous());
                }
                if (msg != null) {
                    if (now < msg.when) {
                        nextPollTimeoutMillis = (int) Math.min(msg.when - now, Integer.MAX_VALUE);
                    } else {
                        mBlocked = false;
                        if (prevMsg != null) {
                            prevMsg.next = msg.next;
                        } else {
                            mMessages = msg.next;
                        }
                        msg.next = null;
                        msg.markInUse();
                        return msg;
                    }
                } else {
                    nextPollTimeoutMillis = -1;
                }

                if (mQuitting) {
                    dispose();
                    return null;
                }

                if (pendingIdleHandlerCount < 0 && (mMessages == null || now < mMessages.when)) {
                    pendingIdleHandlerCount = mIdleHandlers.size();
                }
                if (pendingIdleHandlerCount <= 0) {
                    mBlocked = true;
                    continue;
                }

                if (mPendingIdleHandlers == null) {
                    mPendingIdleHandlers = new IdleHandler[Math.max(pendingIdleHandlerCount, 4)];
                }
                mPendingIdleHandlers = mIdleHandlers.toArray(mPendingIdleHandlers);
            }

            for (int i = 0; i < pendingIdleHandlerCount; i++) {
                final IdleHandler idler = mPendingIdleHandlers[i];
                mPendingIdleHandlers[i] = null;

                boolean keep = false;
                try {
                    keep = idler.queueIdle();
                } catch (Throwable t) {

                }

                if (!keep) {
                    synchronized (this) {
                        mIdleHandlers.remove(idler);
                    }
                }
            }

            pendingIdleHandlerCount = 0;
            nextPollTimeoutMillis = 0;
        }
    }

    // 消息入队
    boolean enqueueMessage(Message msg, long when) {
        if (msg.target == null) {
            throw new IllegalStateException("Message must have a target.");
        }
        if (msg.isInUse()) {
            throw new IllegalStateException(msg + " This message is already in use.");
        }

        synchronized (this) {
            if (mQuitting) {
                IllegalStateException e = new IllegalStateException(
                        msg.target + " sending message to a Handler on a dead thread"
                );
                System.out.println(e);
                msg.recycle();
                return false;
            }

            msg.markInUse();
            msg.when = when;
            Message p = mMessages;
            boolean needWake;
            if (p == null || when == 0 || when < p.when) {
                msg.next = p;
                mMessages = msg;
                needWake = mBlocked;
            } else {
                needWake = mBlocked && p.target == null && msg.isAsynchronous();
                Message prev;
                for (;;) {
                    prev = p;
                    p = p.next;
                    if (p == null || when < p.when) {
                        break;
                    }
                    if (needWake && p.isAsynchronous()) {
                        needWake = false;
                    }
                }
                msg.next = p;
                prev.next = msg;
            }
            if (needWake) {
                nativeWake(mPtr);
            }
        }
        return true;
    }

    boolean hasMessages(Handler h, int what, Object obj) {
        if (h == null) {
            return false;
        }

        synchronized (this) {
            Message p = mMessages;
            while (p != null) {
                if (p.target == h && p.what == what && (obj == null || p.obj == obj)) {
                    return true;
                }
                p = p.next;
            }
            return false;
        }
    }

    // 删除消息
    void removeMessages(Handler h, int what, Object obj) {
        if (h == null) {
            return;
        }

        synchronized (this) {
            Message p = mMessages;
            while (p != null && p.target == h && p.what == what && (obj == null || p.obj == obj)) {
                Message n = p.next;
                mMessages = n;
                p.recycleUnckecked();
                p = n;
            }
            while (p != null) {
                Message n = p.next;
                if (n != null) {
                    if (n.target == h && n.what == what && (obj == null || p.obj == obj)) {
                        Message nn = n.next;
                        n.recycleUnckecked();
                        p.next = nn;
                        continue;
                    }
                }
                p = n;
            }
        }
    }

    void removeMessages(Handler h, Runnable r, Object obj) {
        if (h == null || r == null) {
            return;
        }

        synchronized (this) {
            Message p = mMessages;

            // Remove all messages at front.
            while (p != null && p.target == h && p.callback == r
                    && (obj == null || p.obj == obj)) {
                Message n = p.next;
                mMessages = n;
                p.recycleUnckecked();
                p = n;
            }

            // Remove all messages after front.
            while (p != null) {
                Message n = p.next;
                if (n != null) {
                    if (n.target == h && n.callback == r
                            && (obj == null || n.obj == obj)) {
                        Message nn = n.next;
                        n.recycleUnckecked();
                        p.next = nn;
                        continue;
                    }
                }
                p = n;
            }
        }
    }

    void removeCallbacksAndMessages(Handler h, Object obj) {
        if (h == null) {
            return;
        }

        synchronized (this) {
            Message p = mMessages;

            // Remove all messages at front.
            while (p != null && p.target == h
                    && (obj == null || p.obj == obj)) {
                Message n = p.next;
                mMessages = n;
                p.recycleUnckecked();
                p = n;
            }

            // Remove all messages after front.
            while (p != null) {
                Message n = p.next;
                if (n != null) {
                    if (n.target == h
                            && (obj == null || n.obj == obj)) {
                        Message nn = n.next;
                        n.recycleUnckecked();
                        p.next = nn;
                        continue;
                    }
                }
                p = n;
            }
        }
    }

    // 删除所有消息
    private void removeAllMessageLocked() {
        Message p = mMessages;
        while (p != null) {
            Message n = p.next;
            p.recycleUnckecked();
            p = n;
        }
    }

    // 删除未来消息: now < p.when
    private void removeAllFutureMessageLocked() {
        final long now = System.currentTimeMillis();
        Message p = mMessages;
        if (p != null) {
            if (p.when > now) {
                removeAllMessageLocked();
            } else {
                Message n;
                for (;;) {
                    n = p.next;
                    if (n == null) {
                        return;
                    }
                    if (n.when > now) {
                        break;
                    }
                    p = n;
                }
                p.next = null;
                do {
                    p = n;
                    n = p.next;
                    p.recycleUnckecked();
                } while (n != null);
            }
        }
    }

    // 退出消息队列
    void quit(boolean safe) {
        if (!mQuitAllowed) {
            throw new IllegalStateException("Main thread not allowed to quit.");
        }

        synchronized (this) {
            if (mQuitting) {
                return;
            }
            mQuitting = true;
            if (safe) {
                removeAllFutureMessageLocked();
            } else {
                removeAllMessageLocked();
            }

            nativeWake(mPtr);
        }
    }

    public int postSyncBarrier() {
        return postSyncBarrier(System.currentTimeMillis());
    }

    private int postSyncBarrier(long when) {
        synchronized (this) {
            final int token = mNextBarrierToken++;
            final Message msg = Message.obtain();
            msg.markInUse();
            msg.when = when;
            msg.arg1 = token;

            Message prev = null;
            Message p = mMessages;
            if (when != 0) {
                while (p != null && p.when < when) {
                    prev = p;
                    p = p.next;
                }
            }
            if (prev != null) {
                msg.next = p;
                prev.next = msg;
            } else {
                msg.next = p;
                mMessages = msg;
            }
            return token;
        }
    }

    public void removeSyncBarrier(int token) {
        synchronized (this) {
            Message prev = null;
            Message p = mMessages;
            while (p != null && (p.target != null || p.arg1 != token)) {
                prev = p;
                p = p.next;
            }
            if (p == null) {
                throw new IllegalStateException("has already been removed.");
            }
            final  boolean needWake;
            if (prev != null) {
                prev.next = p.next;
                needWake = false;
            } else {
                mMessages = p.next;
                needWake = (mMessages == null || mMessages.target != null);
            }
            p.recycleUnckecked();
            if (needWake && !mQuitting) {
                nativeWake(mPtr);
            }
        }
    }

    public static interface IdleHandler {
        boolean queueIdle();
    }

}
