package behavioral.iterator.target;

import java.util.Iterator;

public interface Server {
    String getName();

    //增加消息对象的方法
    void addMessage(String message);

    //返回一个迭代器以遍历
    Iterator getIterator();
}
