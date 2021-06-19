package generic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GenericMethod {

    //泛型方法 先声明泛型 返回值及其泛型。 可以是静态或者非静态
    //迭代器遍历hashmap方式
    public static <E,T> Map<E,T> testGeneric(Set<Map.Entry<E,T>> set){
        Map<E,T> hashMap = new HashMap(); //自动类型推断
        Iterator<Map.Entry<E, T>> iteratorSet = set.iterator();
        while(iteratorSet.hasNext()){
            Map.Entry<E, T> next = iteratorSet.next();
            hashMap.put(next.getKey(),next.getValue());
        }

        Set<Map.Entry<E,T>> entry = hashMap.entrySet();
        Iterator<Map.Entry<E,T>> iterator = entry.iterator();
        while(iterator.hasNext()) {
            Map.Entry<E,T> e = iterator.next();
            Object key = e.getKey();
            Object value = e.getValue();
            System.out.println("key:" + key + "\t value:" + value);
        }
        return hashMap;
    }
}
