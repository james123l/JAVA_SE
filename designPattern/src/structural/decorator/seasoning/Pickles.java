package structural.decorator.seasoning;


// 具体装饰类
import structural.decorator.products.Food;

public class Pickles extends BurgerDecorator {
    public Pickles(Food burger) {
        super(burger);
        setDes("pickles");
        setPrice(1.5f);
    }
}
