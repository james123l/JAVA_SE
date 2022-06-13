package behavioral.strategy.strategies.physicalattack;

public class LowPhysicalAttack implements  PhysicalAttackStrategy{
    @Override
    public int physicalAttack() {
        return 10;
    }
}
