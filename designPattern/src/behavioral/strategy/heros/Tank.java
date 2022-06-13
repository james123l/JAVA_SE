package behavioral.strategy.heros;

import behavioral.strategy.strategies.magicalattack.MagicalStrategy;
import behavioral.strategy.strategies.physicalattack.PhysicalStrategy;

public class Tank extends AbsHero{

    public Tank(String name) {
        this.setName(name);
        this.setHp(500);
        this.magicalAttack = MagicalStrategy.getNoMagicalAttack();
        this.physicalAttack = PhysicalStrategy.getLowPhysicalAttack();
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
