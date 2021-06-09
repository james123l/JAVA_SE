package Concurrency.sychronize.sychronizeMethod;

//同步方法
public class SynchronizedMethod {
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
    public static void main(String[] args)  {
        SynchronizedMethod synchronizedMethod = new SynchronizedMethod();
        synchronizedMethod.funThread();
        synchronizedMethod.funRunnable();

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
        //设置循环条件 否则线程不会结束
        int i = 100;
        while (i>0) {
            sale();
            i--;
        }
    }
    private static synchronized void sale(){
        //此处默认监视器是this 所以三个对象的监视分别是三个对象本身
        //此处需要把同步方法设置为静态，让多个对象共享一个方法，此时监视器是WindowT.class
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + ":" + ticket);
            ticket--;
        }

    }
}




class WindowR implements Runnable {
    private static int  ticket = 100;

    //售票逻辑
    @Override
    public void run() {
        //设置循环条件 否则线程不会结束
        int i = 100;
        while (i>0) {
            sale();
            i--;
        }
    }
    private synchronized void sale(){
        //此时默认监视器为this 以为只有一个对象
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + ":" + ticket);
            ticket--;
        }

    }
}