package io.Decorator;

public class ComponentExtendA extends Decorator {
    //constructor
    public ComponentExtendA(Component component) {
        //调用父类构造
        super(component);
    }

    @Override
    public void read() {
        //实现read函数功能拓展
        readExtend1();
        super.read();
        readExtend2();
    }

    //新增拓展方法
    public void readExtend1(){
        System.out.println("ComponentExtendA 新拓展的方法1.");
    }
    public void readExtend2(){
        System.out.println("ComponentExtendA 新拓展的方法2.");
    }
}
