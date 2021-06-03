package iostream.Decorator;

//装饰器父类，为子类进行功能拓展
public class Decorator extends Component{

    //实际有效的类的部分
    private Component component;

    //constructor
    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void read(){
        //把请求转发给component对象
        component.read();
    }


}
