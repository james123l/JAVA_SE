package MultiThread.waitNotifyProduceConsumer.ProduceConsumer;//失败案例
/*
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


public class failed {
    public static void main(String[] args) {
        Sales sales = new Sales();
        Producer producer1 = new Producer(sales);
        Consumer consumer1 = new Consumer(sales);
        Consumer consumer2 = new Consumer(sales);
        Producer producer2 = new Producer(sales);
        Thread thread1 = new Thread(producer1);
        Thread thread2 = new Thread(producer2);
        Thread thread3 = new Thread(consumer1);
        Thread thread4 = new Thread(consumer2);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
/*

class Sales {
    //initialize the count of product as 0
    int product = 0;
}


class Producer implements Runnable{
    Sales sales = null;

    public Producer(Sales sales) {
        this.sales = sales;
    }

    @Override
    public void run() {
        synchronized (this.sales) {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (sales.product == 20) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    sales.product++;
                    sales.notify();
                    //在这里出现了线程安全问题 自增后尚未打印，就被消费--，于是打印的时候打印成原来的数据
                    System.out.println("生产一个商品，剩余商品：" + sales.product);
                }
            }
        }

    }
}


class Consumer implements Runnable{
    Sales sales = null;

    public Consumer(Sales sales) {
        this.sales = sales;
    }

    @Override
    public  void run() {
        synchronized (this.sales) {
            while (true) {
                //设置消费时间 10毫秒消费一个
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (sales.product == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    sales.product--;
                    sales.notify();
                    System.out.println("消费一个商品，剩余商品：" + sales.product);
                }
            }
        }

    }
}

 */
