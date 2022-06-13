package behavioral.chainofresponsebility.handler;

import behavioral.chainofresponsebility.DepositeRequest;

/*
* 抽象职责类 需要所有执行这个职责的类继承这个handler 并且子类需要逐级排成链
* 业务需求： atm处理0-999 银行处理1000- 9999的存款 irs处理10000-99999 更大则需要FR
* */
public abstract class Handler {
    Handler nexthandler;    //下一个处理者
    String name;            // 名字

    public Handler(String name) {
        this.name = name;
    }

    public void setNextHandler(Handler nexthandler) {
        this.nexthandler = nexthandler;
    }

    //存款请求 要求子类实现
    public abstract void processRequest(DepositeRequest request);
}
