package behavioral.mediator.electrics;

import behavioral.mediator.mediators.Mediator;

public class CoffeeMachine extends EColleague {
    public CoffeeMachine(Mediator mediator, String name) {
        super(mediator, name);
        mediator.register(name, this);
    }

    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange, this.name);
    }

    public void startCoffeeMachine() {
        System.out.println("Coffee machine started!");
    }

    public void makeCoffee() {
        System.out.println("Coffee is ready!");
        sendMessage(0);
    }
}
