package creational.factory.pizza;

import creational.factory.pizza.Pizza;

public class PepperPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println(" pepper pizza prepare ");
    }

}