import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE,TYPE_USE, TYPE_PARAMETER })
@Inherited
@Documented
public @interface MyAnnotations {
    //为了实现多个MyAnnotation注解一个类
    MyAnnotation[] value();
}
