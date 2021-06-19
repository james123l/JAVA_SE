package concurrency.auxiliary;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        //设置capacity
        // add方法  添加时如果满 再添加会抛出异常 IllegalStatementException
        // remove方法 如果队列是空 还在remove 会抛出 NoSuchElementException
        //element()  查看当前队首 ，如果是空 抛出异常
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        //有返回值无异常
        blockingQueue.offer('a');   //offer方法添加元素 返回boolean 不会抛出异常
        blockingQueue.peek(); //查看队首 不抛出异常 空返回null
        blockingQueue.poll(); //出队列 如果队列是空 返回null
        //阻塞态
        try {
            blockingQueue.put('a'); //如果添加不进去则需要一直等待
            blockingQueue.take();      //一直等待到可以取出
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //超时等待
        try {
            blockingQueue.offer('a',2, TimeUnit.SECONDS);   //offer方法添加元素 如果无法添加 等待2秒如果还是无法添加后退出
            blockingQueue.poll(2,TimeUnit.SECONDS); //出队列 如果没有元素 等待2秒还是没有就退出
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
