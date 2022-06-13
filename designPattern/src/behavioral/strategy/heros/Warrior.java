package behavioral.strategy.heros;


import behavioral.strategy.strategies.magicalattack.MagicalStrategy;
import behavioral.strategy.strategies.physicalattack.PhysicalStrategy;

public class Warrior extends AbsHero {
    public Warrior(String name) {
        this.setName(name);
        this.physicalAttack = PhysicalStrategy.getHighPhysicalAttack();
        this.magicalAttack = MagicalStrategy.getHighMagicalAttack();
    }
    @Override
    public int magicalAttack() {
        try {
            throw this.exception;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
