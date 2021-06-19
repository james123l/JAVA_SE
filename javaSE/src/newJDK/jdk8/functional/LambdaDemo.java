package newJDK.jdk8.functional;

import java.util.Comparator;
import java.util.function.Consumer;

public class LambdaDemo {
    /*
    * java 8 lambda
    * */
    /**
     * lambda测试
     */
    public void lambdaTest(){
        //举例cpmparator
        Comparator<Integer> comparatortemp = (o1, o2) -> Integer.compare(o1, o2);
        int compare = comparatortemp.compare(1, 2);
        System.out.println(compare);
        /*
         * 参数列表就是抽象方法的参数列表 lambda体就是方法体。
         * lambda 就是函数式接口的实例，只有一个抽象方法
         */
        //01 无参无返回
        Runnable runnable = () -> System.out.println("hello,lambda!");
        runnable.run();


        //02 需要参数(参数列表的类型可以自动推断)，如果一个参数可省略括号 。 无返回值
        //Consumer<String> consumer = (String str) ->{ System.out.println(str); };
        Consumer<String> consumer = str ->{ System.out.println(str); };
        consumer.accept("hello,lambda!");


        //03 有2个以上的参数并且多条语句 有返回值
        Comparator<Integer> comparator = (o1, o2) -> {
            System.out.println(o1+','+o2);
            return o1.compareTo(o2);
        };
        System.out.println(comparator.compare(1, 2));

        //04 有返回值，且只有一条语句 则都可以省略
        Comparator<Integer> comparator01 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(comparator01.compare(1, 2));
    }
}
