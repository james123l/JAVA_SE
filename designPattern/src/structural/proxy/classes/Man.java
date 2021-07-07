package structural.proxy.classes;

//实现类
public class Man implements Human {
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