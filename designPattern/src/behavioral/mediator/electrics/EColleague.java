package behavioral.mediator.electrics;

import behavioral.mediator.mediators.Mediator;

/*
* 一切电子产品类的抽象类
* */
public abstract class EColleague {
    private Mediator mediator;
    public String name;

    public EColleague(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public Mediator getMediator() {
        return this.mediator;
    }

    public abstract void sendMessage(int stateChange);
}
