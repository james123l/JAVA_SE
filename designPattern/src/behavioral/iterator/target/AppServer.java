package behavioral.iterator.target;

import behavioral.iterator.iterators.AppServerIterator;

import java.util.Iterator;

/*
* 使用数组迭代
* */
public class AppServer implements Server {
    private String name;
    //消息队列
    private Message[] messageQueue ;
    //标记消息数量的指针
    int count = 0;

    public AppServer(String name) {
        this.name = name;
        this.messageQueue = new Message[20];
        for (int i = 0; i < 5; i++) {
            messageQueue[i] = new Message(String.valueOf(i));
            count++;
        }
    }

    @Override
    public String getName() {
        return "application server:"+this.name;
    }

    @Override
    public void addMessage(String message) {
        if(count<messageQueue.length) messageQueue[count] = new Message(message);
    }

    @Override
    public Iterator getIterator() {
        return new AppServerIterator(messageQueue);
    }
}
