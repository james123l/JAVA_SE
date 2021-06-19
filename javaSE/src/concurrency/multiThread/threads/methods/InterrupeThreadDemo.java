package concurrency.multiThread.threads.methods;

/*
* interrupt方法终止线程  定义标记法
* 定义标记法停止线程缺点是：
* * 同步方法中不能有wait函数，让两个持有相同锁的 进程都进入阻塞态。
* * 因为如果两个线程都进入了阻塞态，此时两个线程都具有执行条件，在synchronized区域内等待执行权。在其他线程更改了标记后，线程还在执行，不安全。
* interrupt()方法强制唤醒进程并抛出InterruptedException异常，使其具备上处理机的资格
* */
public class InterrupeThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Threadstop threadstop = new Threadstop();
        Thread thread1= new Thread(threadstop);
        thread1.setName("01");
        Thread thread2= new Thread(threadstop);
        thread2.setName("02");
        int count = 0;
        thread1.start(); thread2.start();
        for(;;){
            if(++count>=50){
                threadstop.setFlag(false);
                thread1.interrupt();
                thread2.interrupt();
                break;
            }
            System.out.println("thread is running!"+count);
        }
    }
}
class Threadstop implements Runnable{
    boolean flag = true;
    void setFlag(boolean bool){
        flag = bool;
    }
    @Override
    public synchronized void run() {
        while(flag){
            notify();
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            System.out.println("thread"+Thread.currentThread().getName());
        }
    }
}
/*
thread is running!47
thread
thread
thread is running!48
thread
thread
thread is running!49
thread
运行到这里main函数已经结束，但是并没有jvm退出，因为此时有其他线程仍然在执行。
 */
