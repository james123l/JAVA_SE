package classes.innerclass;

public class Test  {
    public static void main(String[] args) {
        //实例化内部类
        //实例化静态内部类
        Person.Dog dog = new Person.Dog();
        //实例化内部类
        Person person = new Person();   //因为内部类不是静态类 所以在加载外部类的时候 没有加载内部类的信息 内部类因此不能直接实体化
        Person.Bird bird = person. new Bird(); //之前Bird类写在代码块中 导致不能实例化
        bird.display();

    }

}
//此时的Person class只能被Test类使用
class Person{
    String name;

    static class Dog{
        //静态内部类
        String name;
        int age;
        public void bark(){
            System.out.println("Dog class");
        }
    }
    class Bird {
        String name ;
        public void sing() {
            System.out.println("Bird class");
            System.out.print("外部类是:");
            eat();  // 这里需要调用Person类的方法，此时rat函数是省略写法 完整的则需要这样写Person.this.eat();this是指Bird类，
        }
        public void display(){
            //调用外部类变量和函数的方法 以同名成员name为例
            System.out.println(this.name);  //bird的name
            System.out.println(name);  //bird的name
            //如果这个方法形式参数命名为name 这里打印的就是传入的参数name
            System.out.println(Person.this.name);  //Person的name
        }
    }


    public void eat(){
        System.out.println("Person class");
    }

}

/*
内部类： 一个类A在B 中 则 A为内部类， B 为外部类
内部类的分类 ： 成员内部类（静态/非静态）  局部内部类（方法内， 代码块内， 构造器内）
成员内部类： 作为外部类成员：  可以调用外部类的结构，
                          可以被static修饰， 被4种权限 public private等修饰
           作为一个类： 可以定义属性，方法以及构造器。
                        可以被final和abstract 修饰
 */
