package behavioral.chainofresponsebility.handler;

import behavioral.chainofresponsebility.DepositeRequest;

public class FR extends Handler{

    public FR(String name) {
        super(name);
    }

    @Override
    public void processRequest(DepositeRequest request) {
        System.out.println("Deposite:"+ request.getBankID()+":"+request.getAmount()+" approved at FR "+ this.name);
    }
}
