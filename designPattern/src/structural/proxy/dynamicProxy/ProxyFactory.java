package structural.proxy.dynamicProxy;

import structural.proxy.classes.HumanUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
实现动态代理所需要解决的问题
问题一：如何根据加载到内存的被代理类，动态创建一个代理类及其对象
问题二：当通过代理类的对象调用方法的时候，如何动态调用被代理类中的同名方法
 */
public class ProxyFactory {
    //解决问题一
    //调用此方法返回代理类的对象
    public static Object getPorxyInstance(Object obj) {//obj是被代理的对象
        //三个参数分别是 类加载器 实现的接口 InvocationHandler(一个接口)
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        myInvocationHandler.bind(obj);
        //调用此方法返回代理类的对象
        //1. ClassLoader loader ： 指定当前目标对象使用的类加载器, 获取加载器的方法固定
        //2. Class<?>[] interfaces: 目标对象实现的接口类型，使用泛型方法确认类型
        //3. InvocationHandler h : 事情处理，执行目标对象的方法时，会触发事情处理器方法, 会把当前执行的目标对象方法作为参数传入
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), myInvocationHandler);
    }
}
class MyInvocationHandler implements InvocationHandler {
    private Object obj; //被代理类对象
    public void bind(Object obj){
        this.obj = obj;
    }
    //假如被代理类有个方法a，当通过代理类的对象调用方法a的时候，就会自动调用如下方法 invoke()
    //将被代理类要执行的方法a的功能声明在invoke方法中
    @Override
    //三个参数分别是代理类的对象， 需要代理类执行的方法，
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //AOP面向切面编程 把humanutil的方法嵌入到代理类的方法中，此时当代理类调用了方法的时候，可以让humanutil的代码和invoke的method嵌入到一起
        HumanUtil humanUtil = new HumanUtil();
        humanUtil.method01();
        //method即为代理类对象调用的方法，此方法也就作为被代理类对象要调用的方法
        Object returnVal = method.invoke(obj,args);     // 方法对象的invoke函数可以让对象执行这个方法，并且可以传入arg参数
        humanUtil.method02();
        return returnVal;
    }
}
