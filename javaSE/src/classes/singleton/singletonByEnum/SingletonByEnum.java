package classes.singleton.singletonByEnum;


/*
* 枚举类的单例模式不会被反射破坏
* 枚举类型自带单例模式
* */
public enum  SingletonByEnum {
    INSTANCE;
    public SingletonByEnum getInstance(){
        return INSTANCE;
    }
}
