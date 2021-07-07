package structural.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/*
* cglib实现的原理是继承 通过继承被代理类 获得了这个类的子类
* 通过invoke调用父类方法 而不是通过反射
* */
import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {
    //被代理对象
    private Object target;

    //返回一个代理对象
    public Object getProxyInstance(Object obj){
        this.target = obj;
        //创建工具类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(target.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类对象 即被代理对象
        return enhancer.create();
    }

    @Override   //这个方法调用被代理对象的方法
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object returnVal = method.invoke(target, args);
        return returnVal;
    }
}
