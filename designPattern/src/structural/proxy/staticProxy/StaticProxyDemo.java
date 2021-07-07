package structural.proxy.staticProxy;

import structural.proxy.classes.Man;

public class StaticProxyDemo {
    //静态代理 在编译时期就确定了什么对象会被代理
    public static void main(String[] args) {
        Man man = new Man();
        HumanProxy humanProxy = new HumanProxy(man);
        humanProxy.eat("food");
        humanProxy.speak("English");
    }
}