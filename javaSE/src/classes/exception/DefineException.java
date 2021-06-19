package classes.exception;

public class DefineException extends RuntimeException {
    //必须有序列号
    static final long serialVersionUID = -7034897190745766939L;

    public DefineException() {
    }

    public DefineException(String message) {
        super(message);
    }
}
