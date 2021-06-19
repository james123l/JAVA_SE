package newJDK.jdk8.functional;


import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
* 当要传递给lambda的函数体已经有方法实现的时候 使用方法引用
* */
public class MethodReference {

    //测试 对象::方法   要求形参列表和返回值和引用方法相同
    // 对象::静态方法是错误的
    public void test01(){
        PrintStream printStream = System.out;

        //lambda写法
        Consumer<String> consumer01 = str -> System.out.println(str);
        //方法引用
        Consumer<String> consumer = printStream::println;
        consumer.accept("hello");
    }

    //测试 类::静态方法  要求形参列表和返回值和引用方法相同
    public void test02(){
        // 01 比较器
        //lambda写法
        Comparator<Integer> comparator01 = (t1,t2) -> Integer.compare(t1,t2);
        //方法引用
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(1, 2));

        // 02 round
        //lambda写法
        Function<Double, Long> function01 = (num)-> Math.round(num);
        //方法引用
        Function<Double, Long> function = Math::round;
        System.out.println(function.apply(3.14));
    }

    //测试 类::方法
    public void test03(){
        // 01 对比字符串   第一个参数作为非静态方法的调用者出现
        //lambda写法
        Comparator<String> comparator01 = (str1,str2) -> str1.compareTo(str2);
        //方法引用
        Comparator<String> comparator = String::compareTo;

        // 02 仅有一个参数 这个参数是方法的调用者
        //lambda写法
        Function<String,Integer> function01 = (str) -> str.hashCode();
        //方法引用
        Function<String,Integer> function = String::hashCode;
    }

    //构造器引用
    public void constructorReference(){
        //lambda写法
        Supplier<String> supplier01 = ()-> new String("hello");
        //方法引用
        Supplier<String> supplier = String::new;

        //lambda写法
        Function<String,String> function01 = (str)-> new String(str);
        //方法引用
        Function<String,String> function = String::new;
    }

    //数组引用
    public void arrayReference(){
        //lambda写法
        Function<Integer,String[]> function01 = (length)-> new String[length];
        //方法引用
        Function<Integer,String[]> function = String[]::new;
    }
}
