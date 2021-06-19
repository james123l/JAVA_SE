package concurrency.multiThread.sychronize.waitNotify.ProduceConsumer;/*
生产者消费者问题：
生产者生产商品给店员，消费者从店员处消费
店员最多持有20个商品
 */
/*
问题分析：
共享数据：店员/商品
设计继承关系 商品用int ，定义在sales类的内部，并把sales实例化作为构造参数构造producer和comsumer对象。
使用委托模式 ，在producer和comsumer类内部实例化sales对象。
producer和comsumer实现Runnable接口 进行对sales的操作
 */
/*
上一个错误案例的错误：
produce和consume分别委托sales 是错误的 应该把这两个委托给sales，把函数放在sales类内部执行
出现产品递增递减问题：
原因：在同步函数和代码块内部进行了sleep。
例如80毫秒生产者增加一个商品 20毫秒消费一个 那么写在同步区域的话，就不能实现producer每80毫秒抢夺一次锁，也不能保证consumer每20毫秒抢夺一次锁
写在同步区外面 就会让他们每隔一段时间开始抢夺锁
 */



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

        /* 高耦合
        Producer producer = new Producer(sales);
        Consumer consumer = new Consumer(sales);
        producer.start();
        consumer.start();

        Producer producer1 = new Producer(sales);
        Consumer consumer1 = new Consumer(sales);
        producer1.start();
        consumer1.start();
         */

    }
}
class Sales {
    //initialize the count of product as 0
    int product = 0;

    //线程同步 避免安全问题 while循环判断防止虚假唤醒
    public synchronized void produce () {
        while (product == 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product++;
        notifyAll();
        //在这里出现了线程安全问题 自增后尚未打印，就被消费--，于是打印的时候打印成原来的数据
        System.out.println(Thread.currentThread().getName()+"生产一个商品，剩余商品：" + product);
    }
    public synchronized void consume() {
        //while循环判断防止虚假唤醒
        while (product == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product--;
        notifyAll();
        System.out.println(Thread.currentThread().getName()+"消费一个商品，剩余商品：" + product);
    }
}

/**
 * 以下代码高耦合 弃用
 */
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




