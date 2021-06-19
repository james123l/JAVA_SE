package newJDK.jdk8.functional;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionInterfaceDemo {
    /**
     * 四大内置函数式接口测试
     */
    //函数型接口测试 apply方法
    public void functionTest(){
        //函数式接口写法
        Function<String,String> function01 = new Function<String,String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };
        //lambda写法
        Function<String,String> function = str->{return str;};

        System.out.println(function.apply("asd"));
    }


    //断言型接口测试 test方法
    public void predicateTest(){
        ArrayList<String> solution = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>(); list.add("aac");list.add("abc");list.add("dc");list.add("ac");list.add("aaccc");

        // 断言接口 判断是否含有字符串“cc”
        Predicate<String> predicate01 = new Predicate<String>(){
            @Override
            public boolean test(String str) {
                return str.contains("cc");
            }
        };
        for (String s: list) {
            if(predicate01.test(s)) solution.add(s);
        }
        //lambda写法
        Predicate<String> predicate = str ->  str.contains("cc");

        for (String s: solution) {
            System.out.println(s);
        }

    }


    //消费型接口测试 accept方法
    public void consumerTest(){
        //消费型接口 传入但是返回void
        Consumer<String> consumer01 = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };
        Consumer<String> consumer = (str)->{System.out.println(str);};
        consumer.accept("sdadasd");
    }


    //供给接口测试  get方法
    public void supplierTest(){
        //供给接口
        Supplier supplier01 = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1024;
            }
        };

        Supplier supplier = ()->{ return 1024; };
        System.out.println(supplier.get());
    }

}
