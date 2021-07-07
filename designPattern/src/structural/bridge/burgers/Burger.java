package structural.bridge.burgers;

import structural.bridge.meats.Meat;

public abstract class Burger {
    // 组合形式 和meat类组合到一起
    private Meat meat;

    public Meat getMeat() {
        return meat;
    }

    public Burger(Meat meat) {
        this.meat = meat;
        meat.setMeat();
    }
    public void cook(){
        meat.cook();
    }
}
