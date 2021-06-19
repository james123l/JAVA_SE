package newJDK.jdk8.optional;

import java.util.Optional;

public class OptionalDemo {

    //三种方式获取optional对象
    public void getOptional(){
        Inner inner = new Inner();
        //Optional.of 要求inner非空
        Optional<Inner> optionalOf = Optional.of(inner);
        //Optional.ofNullable 可以是null
        Optional<Inner> optionalOfNullable = Optional.ofNullable(inner = null);
        //Optional.empty 获取一个空的optional
        Optional<Object> empty = Optional.empty();
    }
}
