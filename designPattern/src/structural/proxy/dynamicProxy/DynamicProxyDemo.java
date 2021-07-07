package structural.proxy.dynamicProxy;

import structural.proxy.classes.Human;
import structural.proxy.classes.Man;

/*
* jdk动态代理和静态代理需要被代理类实现同一个接口
* */
public class DynamicProxyDemo {
    public static void main(String[] args) {
        Man man = new Man();
        //proxyInstance代理类的对象
        Human proxyInstance = (Human) ProxyFactory.getPorxyInstance(man);
        //被代理类的同名方法调用
        proxyInstance.eat("food");
        proxyInstance.speak("English");
    }
}


