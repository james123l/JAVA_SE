package Concurrency.sychronize.callable_ThreadPool.ThreadPool;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) {
        //=左边是接口，右边是线程池 多态
        ExecutorService executorService =Executors.newFixedThreadPool(10);

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
