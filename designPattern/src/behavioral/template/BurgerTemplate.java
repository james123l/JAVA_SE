package behavioral.template;

/*
* burger的模板制作方法 作为抽象类 让子类去实现父类的方法 从而达到不同子类有不同属性
* 模板方法模式控制了一定的算法流程 使得子类可以根据需求插入不同的数据进入算法
* */
public abstract class BurgerTemplate {
    public Burger burger = new Burger();
    public Burger order(){
        //固定算法流程
        this.meat();
        this.bread();
        if(wantSauce())  this.vegtables();
        this.sauce();
        return burger;
    }
    public void bread(){ }
    public void vegtables(){ }
    public abstract void sauce();
    public abstract void meat();
    public boolean wantSauce(){
        //hook方法 判断是否加sauce
        return true;
    }
}
