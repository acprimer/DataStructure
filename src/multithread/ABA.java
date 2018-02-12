package multithread;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by yaodh on 2018/2/12.
 */
public class ABA {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);

    public static void main(String[] args) {
        // 为用户充值
        for (int i = 0; i < 3; i++) {
            final int ts = money.getStamp();
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.getReference();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20, ts, ts + 1)) {
                                    System.out.println("余额小于20元，充值成功，余额：" + money.getReference() + "元");
                                    break;
                                }
                            } else {
//                                System.out.println("余额大于20元，无须充值");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }

        // 用户消费行为
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        int ts = money.getStamp();
                        Integer m = money.getReference();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10, ts, ts + 1)) {
                                System.out.println("成功消费10元，余额：" + money.getReference() + "元");
                                break;
                            }
                        } else {
//                            System.out.println("没有足够的余额");
                            break;
                        }
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        }.start();
    }
}
