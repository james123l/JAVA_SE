package behavioral.strategy.heros;

import behavioral.strategy.strategies.magicalattack.MagicalStrategy;
import behavioral.strategy.strategies.physicalattack.PhysicalStrategy;

public class Enchanter extends AbsHero{

    public Enchanter(String name) {
        this.setName(name);
        this.magicalAttack = MagicalStrategy.getHighMagicalAttack();
        this.physicalAttack = PhysicalStrategy.getLowPhysicalAttack();
    }

}
