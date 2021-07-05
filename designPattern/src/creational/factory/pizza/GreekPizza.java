package creational.factory.pizza;

import creational.factory.pizza.Pizza;

public class GreekPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println(" greek pizza prepare ");
    }

}