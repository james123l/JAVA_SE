package behavioral.mediator.electrics;

import behavioral.mediator.mediators.Mediator;

public class Curtains extends EColleague {
    public Curtains(Mediator mediator, String name) {
        super(mediator, name);
        mediator.register(name, this);
    }

    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange, this.name);
    }

    public void rollCurtains() {
        System.out.println("Holding Up Curtains!");
    }

}
