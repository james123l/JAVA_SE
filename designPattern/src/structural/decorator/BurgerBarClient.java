package structural.decorator;


import structural.decorator.products.Food;
import structural.decorator.products.ChickenBurger;
import structural.decorator.seasoning.Cheese;
import structural.decorator.seasoning.Lettuce;
import structural.decorator.seasoning.Pickles;

/*
* 汉堡店客户端
* */
public class BurgerBarClient {
    public static void main(String[] args) {
        //chicken burger
        Food chickenBurger = new ChickenBurger();
        //add cheese
        chickenBurger = new Cheese(chickenBurger);
        //add pickle
        chickenBurger = new Pickles(chickenBurger);
        //add lettuce
        chickenBurger = new Lettuce(chickenBurger);
        chickenBurger = new Lettuce(chickenBurger);

        System.out.println("Check:");
        System.out.println(chickenBurger.getDes());
        System.out.println(chickenBurger.cost());
    }
}
