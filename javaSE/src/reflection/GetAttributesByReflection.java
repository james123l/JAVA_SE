package reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class GetAttributesByReflection {
    public static void main(String[] args) {
        Class class_Person = Person.class;
        getMember_Type(class_Person);   //获取成员变量及其属性测试
        getMethod(class_Person); //获取方法
        getSuperClassAttribute(class_Person);//获取泛型父类信息
        getInterfacePackageAnnotation(class_Person);    //获取接口， 所在包,注解
    }
    //获取成员变量及其属性
    public static void  getMember_Type(Class class_Person){
        //获取成员变量
        //getFields函数可以获取当前类及其父类中声明为public的成员变量
        Field[] fields = class_Person.getFields();
        for (Field f: fields) {
            System.out.print(f);
        }
        System.out.println();

        //getDeclaredFields函数可以获取当前运行时类的所有修饰符修饰的成员，不包括父类成员
        Field[] declaredFields = class_Person.getDeclaredFields();
        for (Field f: declaredFields) {
            System.out.print(f);
        }
        System.out.println();

        //获取权限修饰符 数据类型和变量名，在declaredFields中操作
        for (Field f: declaredFields) {
            //1.权限修饰符 在Modifier类中 权限修饰符是个常量 int
            int modifier = f.getModifiers();
            System.out.print(Modifier.toString(modifier) + '\t');
            //2.数据类型, 使用重载的tostring    public Class<?> getType() { return type;}
            Class type = f.getType();
            System.out.println(type.getName() + '\t');
            //3.变量名
            String fName = f.getName();
            System.out.println(fName);
        }
    }
    //获取方法
    public static void getMethod(Class class_Person){
        //getMethods函数可以获取当前类及其父类中声明为public的成员函数
        //getDeclaredMethods函数可以获取当前运行时类的所有修饰符修饰的成员函数，不包括父类成员函数
        //getConstructors函数可以获取当前类中声明为public的构造函数
        //getDeclaredConstructors函数可以获取当前类中所有权限的构造函数
        Method[] methods = class_Person.getDeclaredMethods();
        for (Method m:methods) {
            System.out.print(m);
        }
        for (Method m:methods) {
            //1.获取方法的注解
            Annotation[] annotations =m.getAnnotations();
            for (Annotation a:annotations) {
                System.out.println(a);
            }
            //2.权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + '\t');
            //3.返回值
            System.out.print(m.getReturnType().getName() + '\t');
            //4.方法名
            System.out.println(m.getName() + '(');
            //形参列表
            Class[] parameterTypes = m.getParameterTypes();
            if(!(parameterTypes==null&&parameterTypes.length==0)){
                for (int i = 0; i < parameterTypes.length; i++) {
                    if(i==parameterTypes.length-1){
                        System.out.println(parameterTypes[i].getName() ); break;
                    }
                    System.out.println(parameterTypes[i].getName() );
                }
            }
            System.out.println(')');
            //6.异常
            Class[] exceptionTypes = m.getExceptionTypes();
            if(!(exceptionTypes==null&&exceptionTypes.length==0)){
                System.out.println("throws");
                for (int i = 0; i < parameterTypes.length; i++) {
                    if(i==exceptionTypes.length-1){
                        System.out.println(exceptionTypes[i].getName() ); break;
                    }
                    System.out.println(exceptionTypes[i].getName() );
                }
            }
        }
    }
    //获取泛型父类信息
    public static void getSuperClassAttribute(Class class_Person){
        /*
        此时如果是获得运行时类的对象的泛型信息是获取不了的 如果A<T>的对象a a无法获取a对应的T的类型。
        因为java是泛型抹除的，即编译时不存在泛型， 找到类的时候已经不存在泛型信息了。
         */
        //获取父类
        Class superclass = class_Person.getSuperclass();
        //获取父类及其泛型
        Type genericSuperclass = class_Person.getGenericSuperclass();
        //获取泛型信息
        ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();//获取泛型参数
        for (Type t:actualTypeArguments) {
            System.out.println(t); //Class java.lang.string
            System.out.println(t.getTypeName()); //java.lang.string
        }
    }
    //获取接口， 所在包,注解
    public static void getInterfacePackageAnnotation(Class class_Person){
        Class[] interfaces = class_Person.getInterfaces();
        Package person_package = class_Person.getPackage();
        Annotation[] annotations = class_Person.getAnnotations();
    }
}
