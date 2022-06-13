package behavioral.mediator.electrics;

import behavioral.mediator.mediators.Mediator;

public class TV extends EColleague {
    public TV(Mediator mediator, String name) {
        super(mediator, name);
        mediator.register(name, this);
    }

    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange, this.name);
    }

    public void startTV() {
        System.out.println("Start TV!");
    }

    public void closeTV() {
        System.out.println("Close TV!");
    }
}
