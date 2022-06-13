package behavioral.state.raffleapp.states;

import behavioral.state.raffleapp.RaffleActivity;
import behavioral.state.raffleapp.State;

public class Unready implements State {
    RaffleActivity activity;

    public Unready(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void reduceCredits() {
        activity.setState(activity.getReady());
    }

    @Override
    public boolean raffle() {
        System.out.println("Please pay the credits.");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("Raffle to get prize.");
    }
}
