package structural.flyweight;

public class ConcreteProduct extends AbsProduct {
    /*
    * 产品的具体实现类
    * */
    private String type;
    public String getType() {
        return type;
    }

    public ConcreteProduct(String type) {
        super();
        this.type = type;
    }

    @Override
    public void work(User user) {
        //对父类work进行实现 可以根据业务进行更改
        System.out.println("Concrete Product Work Function With Type:"+ this.type+"\t used by "+user.getName());
    }
}
