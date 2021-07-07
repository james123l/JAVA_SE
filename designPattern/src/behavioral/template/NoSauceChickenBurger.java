package behavioral.template;

public class NoSauceChickenBurger extends BurgerTemplate{

    @Override
    public boolean wantSauce() {
        return false;
    }

    @Override
    public void sauce() {
        //不要sauce 空实现
    }

    @Override
    public void meat() {
        this.burger.setMeat(Burger.Meat.chicken);
    }
}
