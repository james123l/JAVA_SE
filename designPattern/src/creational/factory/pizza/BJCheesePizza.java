package creational.factory.pizza;

public class BJCheesePizza extends Pizza {

    @Override
    public void prepare() {
        setName("Beijing cheese pizza");
        System.out.println(" Beijing cheese pizza prepare");
    }

}
