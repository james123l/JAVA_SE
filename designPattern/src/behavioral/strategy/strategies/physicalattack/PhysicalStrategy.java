package behavioral.strategy.strategies.physicalattack;


public class PhysicalStrategy {
    private static PhysicalAttackStrategy lowPhysicalAttack = new LowPhysicalAttack();
    private static PhysicalAttackStrategy highPhysicalAttack = new HighPhysicalAttack();

    public static PhysicalAttackStrategy getLowPhysicalAttack() {
        return lowPhysicalAttack;
    }

    public static PhysicalAttackStrategy getHighPhysicalAttack() {
        return highPhysicalAttack;
    }
}
