package generic;

public class GenericClass {
    public static void main(String[] args) {
        //泛型可以自动类型推断，并且内部如果是基本类型自动装箱为包装类
        SubPerson1<String,String> subPerson11 = new SubPerson1(322,"hello","world");             //默认为是Object
        SubPerson2<String> subPerson12 = new SubPerson2(322,"hello","world"); //必须为String
    }
}
class Person<T1,T2> {
    int num;
    T1 t1;
    T2 t2;
    Person(int num,T1 t,T2 t2){
        this.num = num;
        this.t1 = t;
        this.t2 = t2;
    }
}
//继承全部
class SubPerson1<T1,T2> extends Person<T1,T2>{
    SubPerson1(int num,T1 t1,T2 t2) {
        super(num, t1,t2);
    }
}
//继承部分泛型
class SubPerson2<T> extends Person<String,T>{
    SubPerson2(int num,String str, T t) {
        super(num, str,t);
    }
}
//全部擦除
class SubPerson3 extends Person<String,Integer>{
    SubPerson3(int num,String str, int id) {
        super(num, str, id);
    }
}
