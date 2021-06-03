package dynamicProxyAndReflection.reflectionMethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UseSpecificAttributes {
    //调用指定的运行时类的信息
    public static void main(String[] args) throws Exception {
        Class class_person = Person.class;
        Person person = (Person) class_person.newInstance(); //创建运行时对象
        func01(class_person,person);//常用方式调用运行时类的指定成员变量
        func02(class_person,person);
        func03(class_person,person);//常用调用方法的方式
        func04(class_person); //通过获取构造器获取对象 不常用
    }

    public static void func01(Class class_person, Person person) throws Exception {
        //getDeclaredField(String filename) 获取名为filename的成员变量，可获取所有权限
        Field name = class_person.getDeclaredField("name");
        //强制可更改
        name.setAccessible(true);
        //设置name
        name.set(person,"Tom");
        //输出name
        System.out.println(name.get(person));
        var copy = name.get(person);
    }
    private static void func02(Class class_person, Person person)throws Exception {
        //只能获取public
        Field name = class_person.getField("name");
    }
    private static void func03(Class class_person, Person person)throws Exception{
        //调用普通成员函数
        Method show = class_person.getDeclaredMethod("show");
        //确保可用
        show.setAccessible(true);
        //method.invoke() 参数1 调用方法的对象 参数2 method方法的参数列表
        //invoke方法返回 method方法的返回值， 如果method是void ，则invoke方法返回null
        Object obj = show.invoke(person,"hello,world!");
        System.out.println(obj);

        //调用静态方法
        Method describe= class_person.getDeclaredMethod("describe");
        describe.setAccessible(true);
        //两种写法都可以
        describe.invoke(null);//describe.invoke(Person.class);
    }
    private static void func04(Class class_person) throws Exception {
        //获取构造器， 参数是构造器的参数列表
        Constructor constructor = class_person.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person person = (Person) constructor.newInstance("Tom");
    }
}
