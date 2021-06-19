package concurrency.multiThread.sychronize.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//同步方法
public class ReetrantLockDemo {
    public static void main(String[] args)  {
        testTicket();
    }
    //测试低耦合
    public static void testTicket(){
        Ticket ticket = new Ticket();
        new Thread(()->ticket.sale(),"A").start();
        new Thread(()->ticket.sale(),"B").start();
        new Thread(()->ticket.sale(),"C").start();
    }
}

/**
 * 解耦合 开发常用方式 单独的资源类 面向对象
 */
class Ticket{
    //lock 是个接口
    // 如果是继承Thread 的子类实例化的线程， 就需要把ReentrantLock定义为静态，用同一个锁去解锁
    //此处的new ReentrantLock(); 参数是boolean true代表是个公平锁 先来先服务，否则不是
    Lock lock = new ReentrantLock();
    private static int  ticket = 100;
    public Ticket(){ };
    public void sale() {
        while (true) {
            lock.lock();
            try{
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }finally {
                lock.lock();
            }
        }
    }
}
