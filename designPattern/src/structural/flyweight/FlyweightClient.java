package structural.flyweight;

/*
* 享元模式：java的包装类型Integer 对-128-127 进行了cache 是享元模式
* 很多池化技术就是享元模式,即多个对象 多个业务可以共享相同功能的一个对象
* 享元模式通常有： 抽象接口定义产品 产品实现类 产品工厂
* */
public class FlyweightClient {
    /*
    * 客户端 对享元模式案例的product进行测试
    * */
    public static void main(String[] args) {
        //创建用户信息对象
        User tom = new User("Tom");
        User kate = new User("Kate");
        User james = new User("James");

        //创建产品池
        ProductFactory factory = new ProductFactory();
        AbsProduct blog_web = factory.getProduct("Blog web");
        AbsProduct dating_web = factory.getProduct("Dating web");
        AbsProduct food_web = factory.getProduct("Food web");

        //测试
        food_web.work(tom);
        dating_web.work(kate);
        food_web.work(james);

    }
}
