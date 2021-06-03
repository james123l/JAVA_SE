
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;


//注解与元注解
@Retention(RetentionPolicy.RUNTIME)
/*retentionpolicy是enum类 参数有
runtime 编译时存在运行时加载 反射必须有runtime的retention注解
class 默认情况 编译时有 运行时不加载
source 编译时会被discard
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE,TYPE_USE, TYPE_PARAMETER })
/*
标记可被注解的对象 类型 方法 参数 构造 等
ElementType.TYPE_USE（表明该注解可以写在使用类型的任何语句中）, ElementType.TYPE_PARAMETER（表明该注解可以写在类型变量的声明语句种） 是java8 的新特性
 */
@Documented
//在被javadoc解析的时候 会被javadoc保留下来
@Inherited
//被修饰的类的子类 具有所有的父类的注解

//java8以后的新注解
//使用时其他元注解必须相同
@Repeatable(MyAnnotations.class)
public @interface MyAnnotation {
    //此处为成员 如果一个注解无成员 则代表他是标记作用\
    //如果只有一个变量 一般用value()
    String value() default "hello";
}
