package behavioral.state.raffleapp.states;

import behavioral.state.raffleapp.RaffleActivity;
import behavioral.state.raffleapp.State;

public class OutOfPrize implements State {
    RaffleActivity activity;

    public OutOfPrize(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void reduceCredits() {
        System.out.println("Out of prize, try next time.");
    }

    @Override
    public boolean raffle() {
        System.out.println("Out of prize, try next time.");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("Out of prize, try next time.");
    }
}
