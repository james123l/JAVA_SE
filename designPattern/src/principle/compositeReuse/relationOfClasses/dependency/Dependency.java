package principle.compositeReuse.relationOfClasses.dependency;

public class Dependency {
    /*
    * 依赖的类的某个方法以被依赖的类作为其参数，或者是class A 的某个方法创造了 class B 的实例，或对class B的静态方法的调用。
    * 如果A依赖于B，那意味着B的变化可能要求A也发生变化；
    * */
    /*
    1) 类中用到了对方
    2) 如果是类的成员属性
    3) 如果是方法的返回类型
    4) 是方法接收的参数类型
    5) 方法中使用到
    */
    /*
    * generaliation ：泛化关系 继承 是一种特殊的依赖
    * implementation ： 实现 （接口）是一种特殊的依赖
    * */
}
class Boat{
    public static void row(){
        System.out.println("开动");
    }
}


class Person{
    //三种依赖

//    依赖的类的某个方法以被依赖的类作为其参数
    public void crossRiver(Boat boat){
        boat.row() ;
    }

//    class A 的某个方法创造了 class B 的实例，
    public void fishing(){
        Boat boat = new Boat() ;
        boat.row() ;
    }

//    对class B的静态方法的调用。
    public void patrol(){
        Boat.row() ;
    }
}
