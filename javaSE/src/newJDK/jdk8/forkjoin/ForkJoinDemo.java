package newJDK.jdk8.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/*
* 分支合并会把一个任务分为多个线程并行执行的多个任务，每个线程都有有自己的任务队列，这个队列是双端队列
* 如果一个线程执行完所有的任务，会从其他线程的任务队列的另一端（尾）进行任务抢夺
* */
public class ForkJoinDemo{

    public static void main(String[] args) {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 50000000000L);

        Long sum = pool.invoke(task);

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());//166-1996-10590
    }

    //java8 新特性
    public void test(){
        Instant start = Instant.now();

        Long sum = LongStream.rangeClosed(0L, 50000000000L)
                .parallel()
                .reduce(0L, Long::sum);

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());//1536-8118
    }

}

class ForkJoinSumCalculate extends RecursiveTask<Long>{

    /**
     *
     */
    private static final long serialVersionUID = -259195479995561737L;

    private long start;
    private long end;

    private static final long THURSHOLD = 10000L;  //临界值

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if(length <= THURSHOLD){
            long sum = 0L;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        }else{
            long middle = (start + end) / 2;

            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork(); //进行拆分，同时压入线程队列

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1, end);
            right.fork(); //

            return left.join() + right.join();
        }
    }

}