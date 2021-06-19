package concurrency.multiThread.sychronize.reentrantLock.producerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class ProducerandConsumer {
    public static void main(String[] args) {
        Sales sales = new Sales();
        new Thread(()-> {
            for (int i = 0; i <100 ; i++) sales .produce();},"producer01").start();
        new Thread(()-> {
            for (int i = 0; i <100 ; i++) sales .consume();},"consumer01").start();
        new Thread(()-> {
            for (int i = 0; i <100 ; i++) sales .produce();},"producer02").start();
        new Thread(()-> {
            for (int i = 0; i <100 ; i++) sales .consume();},"consumer02").start();

    }
}

class Sales {
    //initialize the count of product as 0
    int product = 0;
    //创建对象锁
    Lock lock = new ReentrantLock();
    //通过已有的锁获取该锁上的同步监视器，分别监视生产者消费者
    Condition condition_consumer =  lock.newCondition();//消费者监视器
    Condition condition_producer =  lock.newCondition();//生产者监视器



    public void produce(){
        produceProcess();
    }
    public void consume(){
        consumeProcess();
    }
    //线程同步 避免安全问题
     private void produceProcess () {
        try {
            //生产者上锁 此时消费者不能消费，因为是互斥锁
            lock.lock();
            //while循环判断防止虚假唤醒
            while (product == 20) {
                try {
                    //生产者进入阻塞态 Conditional接口
                    condition_producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            product++;
            //消费者被唤醒
            condition_consumer.signalAll();
            System.out.println(Thread.currentThread().getName()+ "生产一个商品，剩余商品：" + product);
        }finally {
            lock.unlock();
        }
    }
    private void consumeProcess() {
        try {
            //进入消费之后上锁，此时不能生产 因为是互斥锁
            lock.lock();
            //while循环判断防止虚假唤醒
            while (product == 0) {
                try {
                    //消费者进入阻塞态 Conditional接口
                    condition_consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            product--;
            //生产者被唤醒
            condition_producer.signalAll();
            System.out.println(Thread.currentThread().getName()+"消费一个商品，剩余商品：" + product);
        }finally {
            lock.unlock();
        }
    }
}


class Producer extends  Thread{
    private Sales sales = null;

    public Producer(Sales sales) {
        this.sales = sales;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sales.produce();
        }
    }

}

class Consumer extends  Thread{
    private Sales sales = null ;

    public Consumer(Sales sales) {
        this.sales = sales;
    }

    @Override
    public  void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sales.consume();
        }
    }

}






