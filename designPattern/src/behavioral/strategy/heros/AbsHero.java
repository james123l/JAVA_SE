package behavioral.strategy.heros;

import behavioral.strategy.strategies.magicalattack.MagicalAttackStrategy;
import behavioral.strategy.strategies.physicalattack.PhysicalAttackStrategy;

/*
* 抽象英雄类： 实现子类 战士：物理输出  坦克：扛打 法师：魔法输出
* */
public abstract class AbsHero {
    private String name;
    private int hp = 100;
    protected Exception exception = new UnsupportedOperationException("unable to operate.");
    protected PhysicalAttackStrategy physicalAttack;
    protected MagicalAttackStrategy magicalAttack;

    public void reduceHP(int attack){
        this.hp -= attack;
        if(this.hp<=0) System.out.println(this.name+"  dead.");
    }
    public int physicalAttack(){
        if(this.physicalAttack!= null) return physicalAttack.physicalAttack();
        else return 0;
    }
    public int magicalAttack(){
        if(this.magicalAttack!= null) return magicalAttack.magicalAttack();
        else return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setPhysicalAttack(PhysicalAttackStrategy physicalAttack) {
        this.physicalAttack = physicalAttack;
    }

    public void setMagicalAttack(MagicalAttackStrategy magicalAttack) {
        this.magicalAttack = magicalAttack;
    }
}
