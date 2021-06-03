package MultiThread.implementMethod;

import com.sun.tools.javac.Main;
import java.lang.management.ThreadInfo;

public class RunnableThread  {
    public static void main(String[] args) {
        Runnablethread runnableThread = new Runnablethread();
        //runnableThread 和thread不同，可以再次作为参数创造一个新的Thread对象
        Thread thread1 = new Thread(runnableThread);
        thread1.start(); // num = 99
        Thread thread2 = new Thread(runnableThread);
        thread2.start(); // num = 98 因为公用一个对象 所以操作的都是对象runnableThread

    }
}
class Runnablethread implements Runnable{
    private int num = 100 ;
    @Override
    public void run() {
        System.out.println("Thread is running....");
        num--;
    }
}