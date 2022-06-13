package behavioral.state.raffleapp;


/*
* 状态模式 有两部分
* 1. 状态接口或者抽象类，以及具体的状态实现类
* 2. context上下文管理器
* 上下文管理器内有state成员 state实现类内有上下文管理器成员： 可以实现两个类对象之间的相互操作，降低代码耦合度
* 客户端通过操作上下文管理器操作state
* */
public interface State {
    //扣除积分
    void reduceCredits();
    //抽奖
    boolean raffle();
    //兑奖
    void dispensePrize();
}
