package behavioral.mediator.mediators;

import behavioral.mediator.electrics.*;

import java.util.HashMap;

//具体的中介者类
public class ConcreteMediator extends Mediator {
    //存放对象名和对象
    private HashMap<String, EColleague> colleagueMap;
    //存放类名字符串和对象名
    private HashMap<String, String> interMap;

    public ConcreteMediator() {
        colleagueMap = new HashMap<String, EColleague>();
        interMap = new HashMap<String, String>();
    }

    @Override
    public void register(String colleagueName, EColleague EColleague) {
        colleagueMap.put(colleagueName, EColleague);
        if (EColleague instanceof Alarm) {
            interMap.put("Alarm", colleagueName);
        } else if (EColleague instanceof CoffeeMachine) {
            interMap.put("CoffeeMachine", colleagueName);
        } else if (EColleague instanceof TV) {
            interMap.put("TV", colleagueName);
        } else if (EColleague instanceof Curtains) {
            interMap.put("Curtains", colleagueName);
        }
    }


    //1. 根据得到消息，完成对应任务
    //2. 中介者在这个方法，协调各个具体的同事对象，完成任务
    @Override
    public void getMessage(int stateChange, String colleagueName) {
        //处理闹钟发出的消息
        if (colleagueMap.get(colleagueName) instanceof Alarm) {
            if (stateChange == 0) {
                ((CoffeeMachine) (colleagueMap.get(interMap.get("CoffeeMachine")))).startCoffeeMachine();
                ((TV) (colleagueMap.get(interMap.get("TV")))).startTV();
            } else if (stateChange == 1) {
                ((TV) (colleagueMap.get(interMap.get("TV")))).closeTV();
            }

        } else if (colleagueMap.get(colleagueName) instanceof CoffeeMachine) {
            ((Curtains) (colleagueMap.get(interMap.get("Curtains")))).rollCurtains();
        } else if (colleagueMap.get(colleagueName) instanceof TV) {//如果TV发现消息

        } else if (colleagueMap.get(colleagueName) instanceof Curtains) {
            //如果是以窗帘发出的消息，这里处理...
        }

    }

    @Override
    public void sendMessage() {
    }

}