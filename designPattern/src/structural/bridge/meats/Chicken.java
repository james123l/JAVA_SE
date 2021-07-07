package structural.bridge.meats;

public class Chicken implements Meat {
    private String meat;
    private boolean cooked = false;
    @Override
    public void setMeat() {
        this.meat = "chicken";
    }

    public String getMeatName() {
        return meat;
    }
    public boolean ready(){
        return cooked;
    }

    @Override
    public void cook() {
        this.cooked = true;
    }
}
