package behavioral.mediator;

import behavioral.mediator.electrics.Alarm;
import behavioral.mediator.electrics.CoffeeMachine;
import behavioral.mediator.electrics.Curtains;
import behavioral.mediator.electrics.TV;
import behavioral.mediator.mediators.ConcreteMediator;

public class Client {
    public static void main(String[] args) {
        ConcreteMediator concreteMediator = new ConcreteMediator();
        Alarm alarm = new Alarm(concreteMediator, "Alarm");
        Curtains curtain = new Curtains(concreteMediator, "Curtain");
        TV tv = new TV(concreteMediator, "TV");
        CoffeeMachine coffee_machine = new CoffeeMachine(concreteMediator, "Coffee Machine");

        //alarm电器的用法
        alarm.sendAlarm(0);
        coffee_machine.makeCoffee();
        alarm.sendAlarm(1);
    }
}
