package concurrency.auxiliary;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    /*
    * 限流
    * */
    public static void main(String[] args) {
        //限流 只能容纳3个线程
        Semaphore semaphore = new Semaphore(3); //一共三个permit
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();   //获取 空参获取一个
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();            //释放 空参释放一个
                }
                System.out.println(Thread.currentThread().getName()+":acquire");
            }, String.valueOf(i)).start();

        }
    }
}
