package Concurrency.methods.interrupte;

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
