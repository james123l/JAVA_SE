package behavioral.iterator;

import behavioral.iterator.target.Message;
import behavioral.iterator.target.Server;

import java.util.Iterator;
import java.util.LinkedList;

public class OutputAllServerMessages {
    private LinkedList<Server> servers = new LinkedList<>() ;

    public OutputAllServerMessages(Server... servers) {
        for (int i = 0; i < servers.length; i++) {
            this.servers.add(servers[i]);
        }
    }

    public void printAllServer() {
        Iterator<Server> iterator = servers.iterator();

        while(iterator.hasNext()) {
            Server server = iterator.next();
            System.out.println("===== "+server.getName() +"=====" );
            printMessageQueue(server.getIterator());
        }
    }

    public void printMessageQueue(Iterator iterator) {
        while(iterator.hasNext()) {
            Message message = (Message)iterator.next();
            System.out.println(message);
        }
    }
}
