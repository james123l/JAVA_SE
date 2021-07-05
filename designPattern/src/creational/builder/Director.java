package creational.builder;

//指挥者，指定制作流程，返回产品
public class Director {

    Builder builder = null;

    //构造器传入 houseBuilder
    public Director(Builder builder) {
        this.builder = builder;
    }

    //通过setter 传入 houseBuilder
    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    //如何处理建造房子的流程，交给指挥者
    public Construction construct() {
        builder.buildBase();
        builder.buildWalls();
        builder.roof();
        return builder.build();
    }


}