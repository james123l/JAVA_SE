package Concurrency.sychronize;

public class UnsafeDemo {
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
        UnsafeDemo unsafeDemo = new UnsafeDemo();
        unsafeDemo.funThread();
        unsafeDemo.funRunnable();

    }
}


class WindowT extends Thread {

    private static int  ticket = 100;
    public WindowT(String str){
        //此处不能调用currentThread进行赋值，因为线程尚未启动
        this.setName(str);
    }


    //售票逻辑
    @Override
    public void run() {
        while (true) {
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



class WindowR implements Runnable {
    private static int  ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ":" + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }
}