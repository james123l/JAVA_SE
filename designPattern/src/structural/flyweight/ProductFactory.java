package structural.flyweight;

import java.util.HashMap;

public class ProductFactory {
    /*
    * 产品工厂类
    * 需要存储hashmap缓存池 来控制不多制造对象 以节约内存开销
    * */
    private HashMap<String,AbsProduct> cachePool = new HashMap<>();

    public AbsProduct getProduct(String type){
        if(!this.cachePool.containsKey(type)){
            //如果缓存中不存在了这个对象
            this.cachePool.put(type,new ConcreteProduct(type));
        }
        return cachePool.get(type);
    }

    public int getPoolSize(){
        return cachePool.size();
    }
}
