package reflection.dynamicProxy;

public class StaticProxyDemo {
    //静态代理 在编译时期就确定了什么对象会被代理
    public static void main(String[] args) {
        Man man = new Man();
        HumanProxy humanProxy = new HumanProxy(man);
        humanProxy.eat("food");
        humanProxy.speak("English");
    }
}
//代理类代理human这个接口的实现类
class HumanProxy implements  Human{
    public Human human;
    public HumanProxy(Human human){
        this.human = human;
    }

    @Override
    public String speak(String str) {
        return human.speak(str);
    }

    @Override
    public String eat(String str) {
        return human.eat(str);
    }
}