package behavioral.chainofresponsebility.handler;

import behavioral.chainofresponsebility.DepositeRequest;

public class Bank extends Handler{
    public Bank(String name) {
        super(name);
    }

    @Override
    public void processRequest(DepositeRequest request) {
        if(request.getAmount()<9999){
            System.out.println("Deposite:"+ request.getBankID()+":"+request.getAmount()+" approved at bank "+this.name );
        }
        this.nexthandler.processRequest(request);
    }
}
