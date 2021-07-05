package creational.factory.pizza;


public class LDCheesePizza extends Pizza{

    @Override
    public void prepare() {
        setName("London cheese pizza");
        System.out.println(" London cheese pizza prepare");
    }
}
