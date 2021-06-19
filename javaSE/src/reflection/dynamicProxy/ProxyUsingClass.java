package reflection.dynamicProxy;

public class ProxyUsingClass {
    //提供代理demo所需要的一切类和接口
}
//接口
interface   Human {
    public String speak(String str);
    public String eat(String str);
}
//实现类
class Man implements Human{
    //Human factory
    @Override
    public String speak(String str) {
        return str;
    }

    @Override
    public String eat(String str) {
        return str;
    }
}
//AOP
class HumanUtil{
    public void method01(){
        System.out.println("utility method01");
    }
    public void method02(){
        System.out.println("utility method02");
    }
}

