package behavioral.chainofresponsebility.handler;

import behavioral.chainofresponsebility.DepositeRequest;

public class ATM extends Handler{
    public ATM(String name) {
        super(name);
    }

    @Override
    public void processRequest(DepositeRequest request) {
        if(request.getAmount()<1000){
            System.out.println("Deposite:"+ request.getBankID()+":"+request.getAmount()+" approved at ATM "+ this.name);
        }
        this.nexthandler.processRequest(request);
    }
}
