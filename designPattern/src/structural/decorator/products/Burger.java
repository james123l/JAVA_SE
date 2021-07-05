package structural.decorator.products;

/*
* 抽象类的缓冲层 实现计算价格的cost函数 使得子类可以单独计算价格
* */
public class Burger extends Food {
    @Override
    public float cost() {
        return 0;
    }
}
