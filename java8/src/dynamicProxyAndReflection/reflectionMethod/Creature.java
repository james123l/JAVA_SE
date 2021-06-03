package dynamicProxyAndReflection.reflectionMethod;

import java.io.Serializable;

public class Creature<T> implements Serializable {
    private char gender ;
    public double weight;
    private void breath(){
        System.out.println("creature breath.");
    }
    private void eat(){
        System.out.println("creature eat.");
    }
}
