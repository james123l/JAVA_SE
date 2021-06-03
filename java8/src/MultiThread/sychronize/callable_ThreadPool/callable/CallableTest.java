package MultiThread.sychronize.callable_ThreadPool.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        //先实例化一个Callable实现类对象
        NumThread numThread = new NumThread();
        //使用furthertask
        FutureTask futureTask= new FutureTask(numThread);
        //启动futhertask的对象
        new Thread(futureTask).start();
        try {
            //futureTask.get()的返回值就是FutureTask(numThread)中numThread的call方法的返回值
            Object obj = futureTask.get();
            System.out.println(obj.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
class NumThread implements Callable{
    //等同于run方法
    @Override
    public Object call() throws Exception {
        //返回值是int 在这里自动装箱为Integer
        return 1;
    }
}
