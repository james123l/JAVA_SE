package MultiThread.waitNotifyProduceConsumer.ProduceConsumer;/*
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
        Producer producer = new Producer(sales);
        Consumer consumer = new Consumer(sales);
        producer.start();
        consumer.start();

        Producer producer1 = new Producer(sales);
        Consumer consumer1 = new Consumer(sales);
        producer1.start();
        consumer1.start();

    }
}




