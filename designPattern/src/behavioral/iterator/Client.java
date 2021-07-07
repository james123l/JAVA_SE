package behavioral.iterator;

import behavioral.iterator.target.AppServer;
import behavioral.iterator.target.WebServer;

/*
* Arraylist采用了迭代器模式 可以让多种数据类型可以迭代遍历
* */

public class Client {
    public static void main(String[] args) {
        AppServer appServer1 = new AppServer("appserver1");     appServer1.addMessage("appserver1");
        AppServer appServer2 = new AppServer("appserver2");     appServer2.addMessage("appserver2");
        WebServer webServer1 = new WebServer("wenbserver1");    webServer1.addMessage("wenbserver1");
        WebServer webServer2 = new WebServer("wenbserver2");    webServer2.addMessage("wenbserver2");
        OutputAllServerMessages outputAllServerMessages = new OutputAllServerMessages(appServer1,appServer2,webServer1,webServer2);
        outputAllServerMessages.printAllServer();

    }
}
