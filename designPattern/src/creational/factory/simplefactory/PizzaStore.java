package creational.factory.simplefactory;

//客户端 工厂的使用者
public class PizzaStore {

    public static void main(String[] args) {

        //使用简单工厂模式
        //new OrderPizza(new SimpleFactory());
        //System.out.println("~~退出程序~~");

        //static 方法
        new OrderPizza();
    }

}
