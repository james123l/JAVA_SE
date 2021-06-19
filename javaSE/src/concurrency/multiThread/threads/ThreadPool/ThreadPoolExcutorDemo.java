package concurrency.multiThread.threads.ThreadPool;
import java.util.concurrent.*;
/** 四个内置拒绝策略：
 * new ThreadPoolExecutor.AbortPolicy()         // 队列和线程满了，还有线程进来，不处理，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy()    // 哪来的去哪里， 例如main函数调用线程，但是线程池满了，则会由main执行这段代码。
 * new ThreadPoolExecutor.DiscardPolicy()       // 队列和线程满了，丢掉新进入的任务，不会抛出异常！
 * new ThreadPoolExecutor.DiscardOldestPolicy() // 队列和线程满了，尝试去和最早的竞争（如果队列头即将执行，那么会等待到出队列后新任务入队列。否则抛弃任务），不会抛出异常！
 *
 * 设置最大线程数的调优
 * cpu密集型：设置为cpu最大处理线程数Runtime.getRuntime().availableProcessors()
 * io密集型： 取决于多少io密集任务。 如果有20个io密集任务，可以设置为40 这样20个线程执行io的时候可以再执行其他任务
 */
public class ThreadPoolExcutorDemo {
    public static void main(String[] args) {

        // 自定义线程池  ThreadPoolExecutor  较为安全，有7个参数
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,  //核心线程数
                Runtime.getRuntime().availableProcessors(), //最大线程数
                3,  //如果线程没有任务，持续等待的时间和单位，超时如果无任务则关闭线程
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),//阻塞队列的个数
                Executors.defaultThreadFactory(),   //默认的线程工厂
                new ThreadPoolExecutor.DiscardOldestPolicy());  //队列满了，尝试去和最早的竞争，也不会抛出异常！

        //执行线程
        try {
            /*
             最大承载=Deque + max， 线程池内部工作原理：
             先进入的有核心线程池处理 即2个线程，剩余的进入阻塞队列。
             当阻塞队列填满后，就开启线程直到最大线程数（Runtime.getRuntime().availableProcessors()）（此时开启的线程，所有任务结束后会自动关闭）
             当所有线程都启动，并且阻塞队列已满的时候，进行拒绝策略。
             */
            // 超过 RejectedExecutionException
            for (int i = 0; i < 20; i++) {      //20个任务，但是队列只有3 最大线程数12 所以只会打印15个
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" executed.");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}


