package creational.factory.pizza;

public class LDPepperPizza extends Pizza{
    @Override
    public void prepare() {
        setName("London pepper pizza");
        System.out.println(" London pepper pizza prepare");
    }
}