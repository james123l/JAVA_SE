package newJDK.jdk8.functional.defineFunctionalInterface;
/*
* 只有一个方法的接口 函数式接口
* */
@FunctionalInterface
public interface MyFunctionalInterfaceOneGerenic<S> {
     S func(S s);
}
