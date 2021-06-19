package io.stream.Decorator;

public class ComponentTest {
    public static void main(String[] args) {
        //类似于new一个文件对象
        Component component= new TrueComponent();
        //为原始对象透明的增加功能
        Decorator decorator1= new ComponentExtendA(component);
        decorator1.read();
        //这里之所以不报错 是因为底层的实现都是靠Component类
        Decorator decorator2= new ComponentExtendB(decorator1);
        decorator2.read();
    }
}
