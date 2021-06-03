package dynamicProxyAndReflection.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InterfaceAddress;

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
/*
实现动态代理所需要解决的问题
问题一：如何根据加载到内存的被代理类，动态创建一个代理类及其对象
问题二：当通过代理类的对象调用方法的时候，如何动态调用被代理类中的同名方法
 */

class ProxyFactory{
    //解决问题一
    //调用此方法返回代理类的对象
    public static Object getPorxyInstance(Object obj){//obj是被代理的对象
        //三个参数分别是 类加载器 实现的接口 InvocationHandler(一个接口)
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        myInvocationHandler.bind(obj);
        //调用此方法返回代理类的对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),myInvocationHandler);
    }
}
class MyInvocationHandler implements InvocationHandler{
    private Object obj; //赋值时需要使用被代理类对象
    public void bind(Object obj){
        this.obj = obj;
    }
    //当通过代理类的对象，调用方法a的时候，就会自动调用如下方法 invoke()
    //讲被代理类要执行的方法a声明在invoke方法中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //AOP
        HumanUtil humanUtil = new HumanUtil();
        humanUtil.method01();
        //method即为代理类对象调用的方法，此方法也就作为被代理类对象要调用的方法
        Object returnVal = method.invoke(obj,args);
        humanUtil.method02();
        return returnVal;
    }
}