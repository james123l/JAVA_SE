package structural.facade;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public enum Kitchen {
    KITCHEN;
    HashMap<Integer,String> reserve = new HashMap<>();
    public String makeFood(String str){
        return str;
    }
    public void prepare(String order){
        int id = Integer.parseInt(order.substring(0,2));
        String food = order.substring(2);
        reserve.put(id,food);
    }
    public void cancel(int id){
        reserve.remove(id);
    }

}
