package creational.factory.factorymethod;

import creational.factory.pizza.LDCheesePizza;
import creational.factory.pizza.LDPepperPizza;
import creational.factory.pizza.Pizza;

public class LDFactory extends PizzaFactory {

    @Override
    Pizza createPizza(String orderType) {

        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }

}
