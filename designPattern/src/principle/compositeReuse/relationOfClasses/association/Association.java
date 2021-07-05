package principle.compositeReuse.relationOfClasses.association;

public class Association {
}

class Computer{
    //如果computer定义person 那么就是双向的关联
    public void develop(){
        System.out.println("Develop ");
    }
}

class Person{
    private Computer computer ;

    public Person(Computer computer){
        this.computer = computer ;
    }

    public void work(){
        computer.develop() ;
        System.out.println("work");
    }

}
