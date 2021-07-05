package creational.factory.pizza;

public class BJPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("Beijing pepper pizza");
        System.out.println(" Beijing pepper pizza prepare");
    }
}