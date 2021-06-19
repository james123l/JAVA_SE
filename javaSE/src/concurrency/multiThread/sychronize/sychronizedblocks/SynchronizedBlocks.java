package concurrency.multiThread.sychronize.sychronizedblocks;

//代码块同步
public class SynchronizedBlocks {
    public static void main(String[] args)  {
        SynchronizedBlocks synchronizedBlocks = new SynchronizedBlocks();
        synchronizedBlocks.testTicket();
        // JUC.multiThread.funRunnable();

    }
    //测试低耦合
    public  void testTicket(){
        Ticket ticket = new Ticket();
        new Thread(()->ticket.sale(),"A").start();
        new Thread(()->ticket.sale(),"B").start();
        new Thread(()->ticket.sale(),"C").start();
    }
    private int funThread(){
        WindowT window1 = new WindowT("窗口一");
        WindowT window2 = new WindowT("窗口二");
        WindowT window3 = new WindowT("窗口三");
        window1.start();
        window2.start();
        window3.start();
        //全部进程结束 退出线程调用函数
        while(true){
            if((!window1.isAlive())&&(!window2.isAlive())&&(!window3.isAlive())) {
                break;
            }
        }
        return 0;
    }
    private void funRunnable(){
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

/**
 * 解耦合 开发常用方式 单独的资源类 面向对象
 */
class Ticket{
    private static int  ticket = 100;
    public Ticket(){ };
    public void sale() {
        while (true) {
            synchronized (this) {   //此时都是对同一个对象 Ticket的实现类进行操作 所以是this
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

class WindowT extends Thread {
    //锁
    static Object obj = new Object();
    private static int  ticket = 100;
    public WindowT(String str){
        //此处不能调用currentThread进行赋值，因为线程尚未启动
        this.setName(str);
    }


    //售票逻辑
    @Override
    public void run() {
        while (true) {
            //代码块进行线程同步
            synchronized (WindowT.class) {
                //synchronized (this) 是错误的 因为有三个对象 就有三个锁
                //synchronized (obj)  是正确的 这里的obj必须是static 使得多个继承Thread的子类的对象共用一个锁
                //synchronized (WindowT.class) 是正确的 类自身也是只加载一次的对象
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + ticket);
                    //此时线程不安全
                    //如果一个线程在这里下处理机 进入就绪态 那么其他线程也可以进入并执行相同操作
                    //此时可以卖出同样的票 因为ticket尚未--操作
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}



class WindowR implements Runnable {
    private static int  ticket = 100;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                //此处因为都是对WINDOWR的同一个对象进行操作 所以只需要this 就是同一把锁
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}