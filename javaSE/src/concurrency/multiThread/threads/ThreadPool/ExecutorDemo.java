package concurrency.multiThread.threads.ThreadPool;

import java.util.concurrent.*;
/*
 * 一、线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外开销，提高了响应的速度。
 *
 * 二、线程池的体系结构：
 * 	java.util.concurrent.Executor : 负责线程的使用与调度的根接口
 * 		|--**ExecutorService 子接口: 线程池的主要接口
 * 			|--ThreadPoolExecutor 线程池的实现类
 * 			|--ScheduledExecutorService 子接口：负责线程的调度
 * 				|--ScheduledThreadPoolExecutor ：继承 ThreadPoolExecutor， 实现 ScheduledExecutorService
 *
 * 三、工具类 : Executors
 * ExecutorService newFixedThreadPool() : 创建固定大小的线程池
 * ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 * ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程
 *
 * ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。
 */
public class ExecutorDemo {
    /*
    * executor构建线程池的用法，不安全 ，开发常用ThreadPoolExecutor
    * Executor类是ThreadPoolExecutor的代理类
    * */
    public static void main(String[] args) {
        //=左边是接口，右边是线程池 多态
        Executors.newCachedThreadPool();//可伸缩的缓存池，由于设置了最大线程数是Integer.MAX_VALUE 有可能导致OOM 内存溢出
        Executors.newSingleThreadExecutor();//单个线程
        ExecutorService executorService =Executors.newFixedThreadPool(10);  //固定大小的缓存池

        //初始化任务
        NumberThread1 numberThread1 = new NumberThread1();
        NumberThread numberThread = new NumberThread();
        FutureTask futureTask= new FutureTask(numberThread);

        executorService.submit(numberThread);   //适合Callable
        executorService.execute(numberThread1);  //适合Runnable
        try {
            Object obj = futureTask.get();
            System.out.println(obj.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //设置线程池属性 需要把栈内的接口变量转换为 ThreadPoolExecutor
        ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor)executorService;
        threadPoolExecutor.setCorePoolSize(20); //设置核心池大小
        threadPoolExecutor.setMaximumPoolSize(20); //最大线程数
        threadPoolExecutor.setKeepAliveTime(1000,TimeUnit.MILLISECONDS);
        //TimeUnit.MILLISECONDS  //毫秒
        //TimeUnit是一个时间单位类

        executorService.shutdown();


    }
}
class NumberThread implements Callable {

    @Override
    public Object call() throws Exception {
        return 1;
    }
}
class NumberThread1 implements Runnable {

    @Override
    public void  run() {
    }
}
