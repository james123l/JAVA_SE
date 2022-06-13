package behavioral.chainofresponsebility;

public class DepositeRequest {
    private int bankID;
    private int amount;

    public DepositeRequest(int bankID, int amount) {
        this.bankID = bankID;
        this.amount = amount;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
