package structural.decorator.seasoning;


// 具体装饰类
import structural.decorator.products.Food;

public class Lettuce extends BurgerDecorator {
    public Lettuce(Food burger) {
        super(burger);
        setDes("lettuce");
        setPrice(1.0f);
    }
}
