package classes.singleton.passive;

public class Singleton {
    //变为static 可以被static方法调用 也防止漏洞 被创造多次
    private static Singleton single = null;
    public int num =1;
    //私有构造函数 使外部无法构造
    private Singleton(){
        single= this;
        num++;
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
    //第二种方法
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
    static {
        System.out.println("blocks" );
    }
}
