package behavioral.iterator.iterators;

import behavioral.iterator.target.Message;

import java.util.Iterator;

/*
* appserver迭代器
* */
public class AppServerIterator implements Iterator {
    private Message[] messageQueue;
    private int position ; //索引

    public AppServerIterator(Message[] messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public boolean hasNext() {
        if(position >= messageQueue.length || messageQueue[position] == null) {
            return false;
        }else {

            return true;
        }
    }

    @Override
    public Object next() {
        Message message = messageQueue[position];
        position += 1;
        return message;
    }
}
