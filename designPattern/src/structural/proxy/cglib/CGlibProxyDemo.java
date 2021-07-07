package structural.proxy.cglib;

import structural.proxy.classes.Human;
import structural.proxy.classes.Man;

/*
* 子类代理，在内存中构建一个被代理类的子类对象从而实现对目标对象功能的拓展，常用于AOP。 不支持final类的代理
* 需要四个包
* java16 报错Unable to make protected final java.lang.Class java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain) throws java.lang.ClassFormatError accessible: module java.base does not "opens java.lang" to unnamed module @6d5380c2
* */
public class CGlibProxyDemo {
    public static void main(String[] args) {

        Man man = new Man();
        //proxyInstance代理类的对象
        ProxyFactory proxyFactory = new ProxyFactory();
        Human proxyInstance = (Human)proxyFactory.getProxyInstance(man);
        //被代理类的同名方法调用
        proxyInstance.eat("food");
        proxyInstance.speak("English");
    }
}
