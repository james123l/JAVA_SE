package reflection;

@MyAnnotation(value = "class")
public class Person extends Creature<String> implements Comparable<String> ,MyInterface{
    private String name;
    int age;
    public int id;
    public Person(){ }
    private Person(String name){
        this.name = name;
    }
    @MyAnnotation("constructor")
    private Person(String name,int age){
        this.age = age;
        this.name = name;
    }
    @MyAnnotation("method")
    private void show(String msg){
        System.out.println("person show func\t" + msg);
    }
    public String display(String msg){
        return msg;
    }
    public static void describe(){
        System.out.println("person describe static func ");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    @Override
    public void info() {
        System.out.println("person info func");
    }
}
