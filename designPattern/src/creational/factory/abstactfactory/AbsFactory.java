package creational.factory.abstactfactory;

import creational.factory.pizza.Pizza;

//一个抽象工厂模式的抽象层(接口)
/*
* 抽象工厂模式： 是对工厂方法模式的更改，抽象出create的具体细节到一个抽象的接口，由子类实现
* */
public interface AbsFactory {
    //让下面的工厂子类来 具体实现
    public Pizza createPizza(String orderType);
}

