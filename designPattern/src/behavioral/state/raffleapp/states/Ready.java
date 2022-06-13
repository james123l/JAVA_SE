package behavioral.state.raffleapp.states;

import behavioral.state.raffleapp.RaffleActivity;
import behavioral.state.raffleapp.State;

import java.util.Random;

public class Ready implements State {
    RaffleActivity activity;

    public Ready(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void reduceCredits() {
        System.out.println("Credits already reduced!");
    }

    @Override
    public boolean raffle() {
        Random r = new Random();
        int num = r.nextInt(10);
        // 10%中奖机会
        if(num == 0){
            // 改变活动状态为发放奖品 context
            activity.setState(activity.getDispense());
            return true;
        }else{
            System.out.println("Sorry,you didn't get prize.");
            // 改变状态为不能抽奖
            activity.setState(activity.getUnready());
            return false;
        }
    }

    @Override
    public void dispensePrize() {
        System.out.println("Sorry,you didn't get prize.");
    }
}
