package behavioral.template;

public class Client {
    public static void main(String[] args) {
        NoSauceChickenBurger noSauceChickenBurger = new NoSauceChickenBurger();
        Burger orderOfNoSauce = noSauceChickenBurger.order();
        System.out.println(orderOfNoSauce);

        BBQChickenBurger bbqChickenBurger = new BBQChickenBurger();
        Burger orderbbqChickenBurger = bbqChickenBurger.order();
        System.out.println(orderbbqChickenBurger);
    }
}
