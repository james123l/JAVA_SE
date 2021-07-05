package structural.composite;

/*
 * 将军类
 * 联盟>国家>将军和军队
 * */
public class General extends OrganizationComponent{
    //这个类不需要存储信息 因为是叶子节点

    public General(String name, String des) {
        super(name, des);
    }

    @Override
    protected void print() {
        System.out.println(this.getName()+':'+getDes());
    }
}
