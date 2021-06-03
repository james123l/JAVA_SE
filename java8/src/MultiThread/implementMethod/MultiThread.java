package MultiThread.implementMethod;


//通过继承Thread类进行多线程




import com.sun.tools.javac.Main;
import java.lang.management.ThreadInfo;

public class MultiThread  {
    public static void main(String[] args) throws InterruptedException {
        //创建对象并start线程，命名。 在运行之前设置优先级
        //优先级是抢占式线程调度 高优先级先上处理机
        Threadtest threadtest = new Threadtest("thread-1 ");
        threadtest.setName("thread-1");
        threadtest.setPriority(Thread.MAX_PRIORITY);    //max是10 min是1   NORM_PRIORITY 是5
        Thread.currentThread().setName("main thread:");
        //开始thread
        threadtest.start();
        Thread.sleep(2000); //main线程暂停2000毫秒
        // 	threadtest.start(); //这里不能调用，抛出异常IllegalThreadStateException
        threadtest.join(); // threadtest线程申请加入main线程，此时main会释放执行权给threadtest
        //从这一刻起， main线程挂起，进入阻塞态 等待到threadtest线程1执行完成，处理机再继续执行main
        //主线程等待子线程的终止。也就是说主线程的代码块中，如果碰到了t.join()方法，此时主线程需要等待（阻塞），等待子线程结束了(Waits for this thread to die.),才能继续执行t.join()之后的代码块。
        threadtest.stop(); //强制停止
        //线程的生命周期是否继续
        System.out.println(threadtest.isAlive());

    }
}
class Threadtest extends Thread {
    //继承thread类
    //重写run方法
    @Override
    public void run() {
        //把该线程的任务放在run方法里
        System.out.println(getName()+"is running....");
        //打印优先级
        System.out.println(Thread.currentThread().getName()+"线程优先级是"+Thread.currentThread().getPriority());
        //暂停
        try {
            sleep(0x1f4);
            //此时释放锁，但是有机会让更低优先级的进程上处理机
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //线程下处理机
        // yield和currentThread是静态函数 所以可以是Thread.yield() 或者Thread.currentThread().yield();
        this.yield();
        //如果有锁，此时不会释放，但是有机会让同优先级的其他线程上处理及
        System.out.println(Thread.currentThread().getName()+"is finishing....");
    }
    //线程起名
    public Threadtest(String name) {
        super(name);
    }
}