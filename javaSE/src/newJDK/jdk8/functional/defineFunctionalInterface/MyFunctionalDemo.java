package newJDK.jdk8.functional.defineFunctionalInterface;

public class MyFunctionalDemo {
    public static void main(String[] args) {
        String str = "hello";
        int i = 1;

        str = getReverse(str,tostr-> new StringBuffer(String.valueOf(tostr)).reverse().toString());
        System.out.println(str);

        str = "123";
        str = getConvert(str, i , (string,numStr) -> String.valueOf(numStr)+string);
        System.out.println(str);


    }
    public static <S> S getReverse(S s, MyFunctionalInterfaceOneGerenic<S> myinterface){
        S func = myinterface.func(s);
        return func;
    }

    public static <S,T> S getConvert(S s,T t, MyFunctionalInterfaceTwoGeneric<S,T> myinterface){
        S func = myinterface.func(s,t);
        return func;
    }
}

