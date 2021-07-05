package structural.composite;

import java.util.HashSet;


/*
 * 国家类
 * 联盟>国家>将军和军队
 * */
public class Country extends OrganizationComponent {
    //国家中包含的将军的链表
    HashSet<OrganizationComponent> organizationComponents = new HashSet<>();


    public Country(String name, String des) {
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
        //输出国家包含的将军
        System.out.print("country--"+this.getName()+':'+getDes()+'\n'+" contains generals: ");
        for (OrganizationComponent o: organizationComponents) {
            System.out.print(o.getName()+'\t');
        }
        System.out.println();
    }
}
