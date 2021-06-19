package concurrency.multiThread.threads.ThreadPool;

import java.util.Random;
import java.util.concurrent.*;

/*
* 线程调度
* */
public class ScheduledThreadPoolDemo {
    public void test(){
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 5; i++) {
            Future<Integer> result = pool.schedule(new Callable<Integer>(){

                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);//生成随机数
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    return num;
                }

            }, 1, TimeUnit.SECONDS);

            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();
    }
}
