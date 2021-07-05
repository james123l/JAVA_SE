package structural.composite;

import java.util.HashSet;

/*
* 联盟类
* 联盟>国家>将军和军队
* */
public class League extends OrganizationComponent{
    //联盟中包含的国家的链表
    HashSet<OrganizationComponent> organizationComponents = new HashSet<>();


    public League(String name, String des) {
        super(name, des);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }

    @Override
    protected void print() {
        //输出联盟包含的国家
        System.out.print("league--"+this.getName()+':'+getDes()+'\n'+" contains countries: ");
        for (OrganizationComponent o: organizationComponents) {
            System.out.print(o.getName()+'\t');
        }
        System.out.println();
    }
}
