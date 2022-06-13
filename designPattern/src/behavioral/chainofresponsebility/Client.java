package behavioral.chainofresponsebility;

import behavioral.chainofresponsebility.handler.ATM;
import behavioral.chainofresponsebility.handler.Bank;
import behavioral.chainofresponsebility.handler.FR;
import behavioral.chainofresponsebility.handler.IRS;

public class Client {
    public static void main(String[] args) {
        ATM atm = new ATM("Citi ATM-01");
        Bank bank = new Bank("Citi Flushing");
        IRS irs = new IRS("IRS NY");
        FR fr = new FR("Federal Reserve");
        atm.setNextHandler(bank);
        bank.setNextHandler(irs);
        irs.setNextHandler(fr);

        atm.processRequest(new DepositeRequest(12252,55860));
    }
}
