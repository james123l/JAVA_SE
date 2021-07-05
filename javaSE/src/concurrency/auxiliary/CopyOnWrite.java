package concurrency.auxiliary;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWrite {
    /*
    * 并发环境下三种替换方式： CopyOnWriteArrayList  CopyOnWriteArraySet  ConcurrentHashMap
    * 不同的是 1.7的concurrentHashmap采用了锁分段机制， 即例如数组是16 有 16 个链表 ， 每个链表都有锁，支持了并行访问，1.8采用cas，不需要下处理机 更加高效
    * 还有一些容器 concurrentSkipListMap ConcurrentSkipListSet
    * */
    public void testCopyOnWriteArrayList(){
        //并发环境下arraylist不安全 可以采用Collections.sychronized 但是效率低
        /*
        * copy on write 写入时复制 每次写入 都会复制一个与原数据相互独立的副本，所以copyonwrite不适合添加操作效率过低，适合迭代操作
        * */
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->list.add(UUID.randomUUID().toString().substring(0,5)),String.valueOf(i)).start();
        }
    }
    public void testCopyOnWriteArraySet(){

        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->set.add(UUID.randomUUID().toString().substring(0,5)),String.valueOf(i)).start();
        }
    }
    public void testConcurrentHashMap(){

        Map<String,String> map = new ConcurrentHashMap();
        for (int i = 0; i < 10; i++) {
            new Thread(()->map.put(Thread.currentThread().getName(),  UUID.randomUUID().toString().substring(0,5))).start();
        }
    }
}
