package structural.bridge.burgers;

import structural.bridge.meats.Meat;

public class WhiteBreadBurger extends Burger {
    public WhiteBreadBurger(Meat meat) {
        super(meat);
        meat.cook();
    }
    @Override
    public void cook(){
        if(this.getMeat().ready()) System.out.println("white bread "+this.getMeat().getMeatName()+ " burger ready");
    }
}
