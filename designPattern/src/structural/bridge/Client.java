package structural.bridge;

import structural.bridge.burgers.WhiteBreadBurger;
import structural.bridge.burgers.WholeWheatBurger;
import structural.bridge.meats.Beef;
import structural.bridge.meats.Chicken;


/*
* 桥接模式解决了不同种类的对象组合在一起的排列组合问题
* 实例： jdbc的driver 和 drivermanager
* */
public class Client {
    public static void main(String[] args) {
        new WhiteBreadBurger(new Chicken()).cook();
        new WholeWheatBurger(new Beef()).cook();
    }
}
