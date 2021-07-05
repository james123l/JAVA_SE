package structural.decorator.seasoning;


// 具体装饰类
import structural.decorator.products.Food;

public class Cheese extends BurgerDecorator {
    public Cheese(Food burger) {
        super(burger);
        setDes("cheese");
        setPrice(2.0f);
    }
}
