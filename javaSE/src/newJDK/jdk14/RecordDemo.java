package newJDK.jdk14;

/*
*  record 类是默认final 无法被继承
*  record 类不能做：
*  1.不能定义非静态成员
*  2.不能是抽象类
*  3.不能写出显示父类
* */
public record RecordDemo() {
    /*
    * 这个record类具有一切的getter  tostring 构造等方法
    * */
}
record RecordDemoParameter(int age,String name) {
    /*
     * 这个record类具有一切的getter tostring RecordDemoParameter(int age,String name)有参构造等方法
     *
     * 注意： 这些参数都是final
     * */
    //可以继续学静态成员 静态函数和构造函数 实例方法
    public static String partner ;
    public static void method(){ }
    public RecordDemoParameter(String str){
        this(0,str);
    }
    public String getNameUpperCase(){
        return this.name.toUpperCase();
    }
}
