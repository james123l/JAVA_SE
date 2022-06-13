package behavioral.state.raffleapp;


import behavioral.state.raffleapp.State;
import behavioral.state.raffleapp.states.Dispense;
import behavioral.state.raffleapp.states.OutOfPrize;
import behavioral.state.raffleapp.states.Ready;
import behavioral.state.raffleapp.states.Unready;

//content上下文管理器 抽奖活动类
public class RaffleActivity {
    private State state = null;
    // 奖品数量
    private int count = 0;

    // 四个属性，表示四种状态
    private State unready = new Unready(this);
    private State ready = new Ready(this);
    private State dispense =   new Dispense(this);
    private State outOfPrize = new OutOfPrize(this);

    //1. 初始化当前的状态为 unready
    //2. 初始化奖品的数量
    public RaffleActivity( int count) {
        this.state = getUnready();
        this.count = count;
    }

    //扣除抽奖需要的积分
    public void reduceCredits(){
        state.reduceCredits();
    }

    //抽奖
    public void raffle(){
        // 如果当前的状态是抽奖成功
        if(state.raffle()){
            //领取奖品
            state.dispensePrize();
        }

    }

    //每领取一次奖品，count--
    public int getCount() {
        int curCount = count;
        count--;
        return curCount;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getUnready() {
        return unready;
    }

    public void setUnready(State unready) {
        this.unready = unready;
    }

    public State getReady() {
        return ready;
    }

    public void setReady(State ready) {
        this.ready = ready;
    }

    public State getDispense() {
        return dispense;
    }

    public void setDispense(State dispense) {
        this.dispense = dispense;
    }

    public State getOutOfPrize() {
        return outOfPrize;
    }

    public void setOutOfPrize(State outOfPrize) {
        this.outOfPrize = outOfPrize;
    }
}
