
package classes.annotation;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

/*
* @MyAnnotations({@MyAnnotation("abc"),@MyAnnotation("def")})这是java8之前的做法 因为没有可重复注解
* jdk8 之后 这个注解写完之后直接与 MyAnnotation 利用@Repeatable(MyAnnotations.class)绑定
 * */

@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_USE, TYPE_PARAMETER })
@Inherited
@Documented
public @interface MyAnnotations {
    //为了实现多个MyAnnotation注解一个类
    MyAnnotation[] value();
}
