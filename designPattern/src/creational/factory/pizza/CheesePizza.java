package creational.factory.pizza;

import creational.factory.pizza.Pizza;

public class CheesePizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println(" cheese pizza prepare ");
    }

}