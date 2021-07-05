package structural.composite;

/*
* 组合模式的OrganizationComponent 是处理单位对象的抽象类，制定规范
* 组合模式 ： 把具有包含关系的一些对象 进行同级处理 即都继承同一个抽象类 OrganizationComponent，然后再进行组合 即 联盟>国家>将军和军队 联盟由国家组成 国家由将军组成
 * */
/*
* java的组合模式实例是hashmap OrganizationComponent是map接口和abstrcthashmap抽象类 hashmap是由node类组合而成
* */
public abstract class OrganizationComponent {

    private String name; // 名字
    private String des; // 说明

    protected void add(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent organizationComponent) {
        //默认实现
        throw new UnsupportedOperationException();
    }

    //构造器
    public OrganizationComponent(String name, String des) {
        super();
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    //方法print, 做成抽象的, 子类都需要实现
    protected abstract void print();
}
