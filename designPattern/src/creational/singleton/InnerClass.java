package creational.singleton;

public class InnerClass {
    /*
    * 使用静态内部类的单例模式
    * */
}
class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static synchronized Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}