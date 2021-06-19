package newJDK.jdk14;

public class InstanceOfDemo {
    public void testNewInstanceOf(Object o){
        //以前的o如果是null 会判断为false 现在也是
        //不需要手动类型转换
        if(o instanceof String str){
            System.out.println(str);
        }
    }
}
