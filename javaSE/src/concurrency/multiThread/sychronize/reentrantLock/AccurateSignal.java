package concurrency.multiThread.sychronize.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* 精确唤醒
* */
public class AccurateSignal {
    /*
    * 按照顺序唤醒ABC
    * */
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()-> {
            for (int i = 0; i <10 ; i++) data.pirintA(); ;},"A").start();
        new Thread(()-> {
            for (int i = 0; i <10 ; i++) data.pirintB();},"B").start();
        new Thread(()-> {
            for (int i = 0; i <10 ; i++) data.pirintC();},"C").start();

    }
}
class Data{
    private Lock lock = new ReentrantLock();
    private char currentRunning = 'A';
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    public void pirintA(){
        lock.lock();
        try {
            while(currentRunning != 'A'){
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName()+" is running.");
            currentRunning = 'B';
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void pirintB(){
        lock.lock();
        try {
            while(currentRunning != 'B'){
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName()+" is running.");
            currentRunning = 'C';
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void pirintC(){
        lock.lock();
        try {
            while(currentRunning != 'C'){
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName()+" is running.");
            TimeUnit.SECONDS.sleep(3);
            currentRunning = 'A';
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
