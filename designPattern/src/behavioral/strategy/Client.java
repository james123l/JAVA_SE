package behavioral.strategy;

import behavioral.strategy.heros.Enchanter;
import behavioral.strategy.heros.Tank;
import behavioral.strategy.heros.Warrior;
import behavioral.strategy.strategies.physicalattack.PhysicalStrategy;

/*
* 策略模式是： 把对象Hero的行为算法（物理和魔法攻击）单独抽象出来 和对象组合在一起， 在需要的时候重新设定，以改变对象的行为。、
* 实例是： Arrays类（对象）的sort 通过comparator（策略接口（行为算法））组合在一起，实现升序降序自定义排序
*
* */
public class Client {
    public static void main(String[] args) {
        Warrior garen = new Warrior("Garen");
        Enchanter fox = new Enchanter("Fox");
        Tank bear = new Tank("Bear");
        bear.reduceHP(garen.physicalAttack());
        bear.reduceHP(fox.physicalAttack());
        System.out.println(bear.getHp());

        //改变fox的行为
        fox.setPhysicalAttack(PhysicalStrategy.getHighPhysicalAttack());
        bear.reduceHP(fox.physicalAttack());
        System.out.println(bear.getHp());
    }
}
