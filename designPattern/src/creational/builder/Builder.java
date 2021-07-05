package creational.builder;

// 抽象的建造者，建造者生产product
//子类完成具体的建造过程
/*
* 制造者模式：stringbuilder
* */
public abstract class Builder {

    protected Construction construction = new Construction();

    //将建造的流程写好, 抽象的方法
    public abstract void buildBase();
    public abstract void buildWalls();
    public abstract void roof();

    //建造房子好， 将产品(房子) 返回
    public Construction build() {
        return construction;
    }

}