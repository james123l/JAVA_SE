package structural.decorator.seasoning;

import structural.decorator.products.Food;

import java.util.Arrays;

/*
* 装饰器类
* jdk源码：
* 基类：InputStream抽象类
*   子类 - 产品：FileInputStream StringBufferInputStream ByteArrayInputStream
*   装饰器子类 ：FilterInputStream(装饰器)， 内部有成员变量 protected volatile InputStream in;
*       FilterInputStream的子类： BufferInputStream DataInputStream LineNumberInputStream
* */
public class BurgerDecorator extends Food {
    private Food burger;

    public BurgerDecorator(Food burger) { //组合
        this.burger = burger;
    }

    @Override
    public float cost() {
        // getPrice 自己价格
        return super.getPrice() + burger.cost();
    }

    @Override
    public String getDes() {
        // obj.getDes() 输出被装饰者的信息
        return des + " " + getPrice() + " + " + burger.getDes();
    }
}
