package structural.proxy.staticProxy;

import structural.proxy.classes.Human;

//代理类代理human这个接口的实现类 通过实现human类获得和代理类同名的方法
public class HumanProxy implements Human {
    public Human human;

    public HumanProxy(Human human) {
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
