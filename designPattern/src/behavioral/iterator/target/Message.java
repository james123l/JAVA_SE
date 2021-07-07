package behavioral.iterator.target;

public class Message {
    //消息列表的消息内容 由服务器类发出和接收
    private String message;

    public Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                '}';
    }
}
