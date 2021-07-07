package structural.bridge.burgers;

import structural.bridge.meats.Meat;

public class WholeWheatBurger extends Burger {
    public WholeWheatBurger(Meat meat) {
        super(meat);
        meat.cook();
    }
    @Override
    public void cook(){
        if(this.getMeat().ready()) System.out.println("whole wheat "+this.getMeat().getMeatName()+ " burger ready");
    }
}
