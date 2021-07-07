package structural.bridge.meats;

public class Beef implements Meat {
    private String meat;
    private boolean cooked = false;
    @Override
    public void setMeat() {
        this.meat = "beef";
    }

    public String getMeatName() {
        return meat;
    }
    public boolean ready(){
        return cooked;
    }

    @Override
    public void cook() {
        cooked = true;
    }
}
