package io.stream.Decorator;

public class ComponentExtendB extends Decorator {
        //constructor
        public ComponentExtendB(Component component) {
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
            System.out.println("ComponentExtendB 新拓展的方法1.");
        }
        public void readExtend2(){
            System.out.println("ComponentExtendB 新拓展的方法2.");
        }

}
