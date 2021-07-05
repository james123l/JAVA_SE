package creational.singleton.nopassive;
/*
* 懒汉式 消耗空间 Runtime类
* */
public class Singleton {
    //变为static 可以被static方法调用 也防止漏洞 被创造多次
    private static  Singleton single = new Singleton();
    public int num =1;
    //私有构造函数 使外部无法构造
    private Singleton(){
        single= this;
        num++;
    }
    //外部函数获得实例化对象的方法 Singleton single = Singleton.getDemo();
    public static Singleton getDemo(){
        if(single == null){
            single = new Singleton();
        }
        return single;
    }
    static {
        System.out.println("blocks" );
    }
}