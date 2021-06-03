package MultiThread.sychronize.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

//同步方法
public class MultiThread  {
    public static void main(String[] args)  {
        WindowR windowr = new WindowR();
        Thread window1 = new Thread(windowr);
        Thread window2 = new Thread(windowr);
        Thread window3 = new Thread(windowr);
        window1.setName("window1");
        window2.setName("window2");
        window3.setName("window3");
        window1.start();
        window2.start();
        window3.start();

    }
}


class WindowR implements  Runnable{
    ReentrantLock reentrantLock = new ReentrantLock();
    // 如果是继承Thread 的子类实例化的线程， 就需要把ReentrantLock定义为静态，用同一个锁去解锁
    //此处的new ReentrantLock(); 参数是boolean true代表是个公平锁 先来先服务，否则不是
    private static int  ticket = 100;

    //售票逻辑
    @Override
    public void run() {
        while (true) {
            try {
                //上锁
                reentrantLock.lock();
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }finally {
                //解锁
                reentrantLock.unlock();
            }

        }
    }

}