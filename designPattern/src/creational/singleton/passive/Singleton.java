package creational.singleton.passive;
/*
* 饿汉式 需要解决线程安全问题
* */
public class Singleton {
    //变为static 可以被static方法调用 也防止漏洞 被创造多次
    /*
    * 使用volatile可以避免指令重排
    * 指令重排可能对懒汉式单例模式造成风险。 例如线程A B
    * A getDemo的时候 single ==  null, 所以 需要： 开辟栈空间， 开辟堆空间 ， 在堆空间初始化， 并把栈空间指向堆
    * 如果A 在此时尚未初始化完成， 但是栈空间已经指向堆空间， 这个时候single ！=  null，B 如果此时进入 则会获取一个尚未完成的堆空间的对象，造成异常。
    * */
    private static volatile Singleton single = null;
    private static boolean flag = false;    //阻止通过反射破坏单例
    //私有构造函数 使外部无法构造
    private Singleton(){
        synchronized (Singleton.class){
            if (flag == false){  //此时依然不能阻止通过反射更改flag 单例依旧不安全
                single= this;
                flag = true;
            }else {
                throw new RuntimeException("do not get more instance by reflection");
            }
        }
    }
    //外部函数获得实例化对象的方法 Singleton single = Singleton.getDemo();
    //第一种方法
    /*
    public static synchronized Singleton getDemo(){
        if(single == null){
            single = new Singleton();
        }
        return single;
    }
     */
    //第二种方法 DCL双重检测
    public static Singleton getDemo(){
        //此时先判断是为了提升效率，减少在线程同步时所产生的等待时间
        //如果single不是null，那么其他线程无需进入 synchronized的等待
        if(single == null) {
            synchronized (single ){
                if(single == null) single = new Singleton();
            }
        }
        return single;
    }
}
