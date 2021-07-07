package behavioral.template;

public class BBQChickenBurger extends BurgerTemplate{
    @Override
    public void sauce() {
        this.burger.setSauce(Burger.Sauce.bbq);
    }

    @Override
    public void meat() {
        this.burger.setMeat(Burger.Meat.chicken);
    }
}
