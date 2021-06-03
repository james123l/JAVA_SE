import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.util.ArrayList;

public class TestAnnotation {
    public static void main(String[] args) {
        //测试注解继承性
        Student student = new Student();
        //获取类
        Class classes = student.getClass();
        //获取注解
        Annotation[] annotations = classes.getAnnotations();
        for (Annotation a: annotations) {
            System.out.println(a);
            //打印出MyAnnotation
        }


    }
}
//java8之前的写法   以两个MyAnnotation作为以数组参数
//@MyAnnotations({@MyAnnotation ,@MyAnnotation  })
@MyAnnotation("abc")
@MyAnnotation("def")
class Person<@MyAnnotation("abc") T >{
    // @MyAnnotation("abc") T  使用了ElementType.TYPE_PARAMETER
    public void show( ) throws @MyAnnotation RuntimeException{
        //ElementType.TYPE_USE
        int num = (@MyAnnotation int)10l;
        ArrayList<@MyAnnotation String> list = new ArrayList<>();
    }

}
class Student extends Person{

}
