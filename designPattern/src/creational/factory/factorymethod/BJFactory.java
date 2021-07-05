package creational.factory.factorymethod;


import creational.factory.pizza.BJCheesePizza;
import creational.factory.pizza.BJPepperPizza;
import creational.factory.pizza.Pizza;

public class BJFactory extends PizzaFactory {


    @Override
    Pizza createPizza(String orderType) {

        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
        }
        return pizza;
    }

}
