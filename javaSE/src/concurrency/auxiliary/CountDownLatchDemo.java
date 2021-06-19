package concurrency.auxiliary;

import java.util.concurrent.CountDownLatch;
/*
* 减法计数器
* */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 11; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();//计数器减1
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await(); //等待计数器归0 才进行下一步操作。 可能超时等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all tasks done!");
    }
}
