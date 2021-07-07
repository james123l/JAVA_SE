package behavioral.iterator.target;

import behavioral.iterator.iterators.WebServerIterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
* 使用队列/ 链表迭代
* */
public class WebServer implements Server{
    private String name;
    //消息队列
    private Queue<Message> messageQueue;

    public WebServer(String name) {
        this.name = name;
        messageQueue = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            messageQueue.add(new Message(String.valueOf(i)));
        }
    }

    @Override
    public String getName() {
        return "web server:"+ this.name;
    }

    @Override
    public void addMessage(String message) {
        messageQueue.add(new Message(message));
    }

    @Override
    public Iterator getIterator() {
        return new WebServerIterator(messageQueue);
    }
}
