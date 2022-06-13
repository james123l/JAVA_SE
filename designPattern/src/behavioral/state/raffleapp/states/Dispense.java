package behavioral.state.raffleapp.states;

import behavioral.state.raffleapp.RaffleActivity;
import behavioral.state.raffleapp.State;

public class Dispense implements State {
    RaffleActivity activity;

    public Dispense(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void reduceCredits() {
        System.out.println("Credits already reduced!");
    }

    @Override
    public boolean raffle() {
        System.out.println("You are already raffled!");
        return false;
    }

    @Override
    public void dispensePrize() {
        if(activity.getCount() > 0){
            System.out.println("Get the peize!");
            activity.setState(activity.getUnready());
        }else{
            System.out.println("Sorry,all prize was given!");
            activity.setState(activity.getOutOfPrize());
            //System.exit(0);
        }
    }
}
