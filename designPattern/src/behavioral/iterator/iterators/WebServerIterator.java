package behavioral.iterator.iterators;

import behavioral.iterator.target.Message;

import java.util.Iterator;
import java.util.Queue;

/*
* webserver迭代器
* */
public class WebServerIterator implements Iterator {
    private Queue<Message> masageQueue;
    private int index = -1;//索引


    public WebServerIterator(Queue<Message> masageQueue) {
        this.masageQueue = masageQueue;
    }

    @Override
    public boolean hasNext() {
        if(masageQueue.isEmpty()) {
            return false;
        } else {
            return true;
        }
        //链表方法
//        if(index >= masageQueue.size() - 1) {
//            return false;
//        } else {
//            index += 1;
//            return true;
//        }
    }

    @Override
    public Object next() {
        return masageQueue.poll();
    }
}
