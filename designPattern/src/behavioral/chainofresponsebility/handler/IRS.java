package behavioral.chainofresponsebility.handler;

import behavioral.chainofresponsebility.DepositeRequest;

public class IRS extends Handler{
    public IRS(String name) {
        super(name);
    }

    @Override
    public void processRequest(DepositeRequest request) {
        if(request.getAmount()<99999){
            System.out.println("Deposite:"+ request.getBankID()+":"+request.getAmount()+" approved at IRS "+this.name);
        }
        this.nexthandler.processRequest(request);
    }
}
