package concurrency.multiThread.sychronize.waitNotify.waitnotify;

//同步方法
public class WaitNotifyDemo {
    public static void main(String[] args)  {
        Communication communication = new Communication();
        Thread thread1 = new Thread(communication);
        Thread thread2 = new Thread(communication);
        thread1.setName("Thread1");
        thread2.setName("Thread2");
        thread1.start();    thread2.start();
    }
}


class Communication implements  Runnable{
    public static int i = 1;
    @Override
    public synchronized void run() {
        while (true) {
            notify();   //notify 函数可以唤醒优先级高的线程 同样优先级随机唤醒
            notifyAll();    //唤醒所有等待线程
            if (i < 101) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                i++;
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}