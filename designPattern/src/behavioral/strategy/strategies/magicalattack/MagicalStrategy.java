package behavioral.strategy.strategies.magicalattack;

public class MagicalStrategy {
    private static MagicalAttackStrategy noMagicalAttack = new NoMagicalAttack();
    private static MagicalAttackStrategy highMagicalAttack = new HighMagicalAttack();

    public static MagicalAttackStrategy getNoMagicalAttack() {
        return noMagicalAttack;
    }

    public static MagicalAttackStrategy getHighMagicalAttack() {
        return highMagicalAttack;
    }
}
