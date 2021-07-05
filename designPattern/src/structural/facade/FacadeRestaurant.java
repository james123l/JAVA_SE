package structural.facade;

/*
* 外观模式 ： 通过组合其他功能来减少客户端直接与复杂的业务逻辑接触
* */
/*
1) 外观模式对外屏蔽了子系统的细节，因此外观模式降低了客户端对子系统使用的复杂性
2) 外观模式对客户端与子系统的耦合关系，让子系统内部的模块更易维护和扩展
3) 通过合理的使用外观模式，可以帮我们更好的划分访问的层次
4) 当系统需要进行分层设计时，可以考虑使用Facade模式
5) 在维护一个遗留的大型系统时，可能这个系统已经变得非常难以维护和扩展，此时可以考虑为新系统开发一个Facade类，来提供遗留系统的比较清晰简单的接口，让新系统与Facade类交互，提高复用性
6) 不能过多的或者不合理的使用外观模式，使用外观模式好，还是直接调用模块好。要以让系统有层次，利于维护为目的。
* */
public class FacadeRestaurant {
    private Kitchen kitchen;
    private FrontDesk frontDesk;
    private Delivery delivery;
    public FacadeRestaurant() {
        super();
        this.delivery = Delivery.DELIVERY;
        this.frontDesk = FrontDesk.FRONT_DESK;
        this.kitchen = Kitchen.KITCHEN;
    }
    public String placeOrder(){
        String order = frontDesk.getOrder();
        String address = frontDesk.getAddress();
        String food = kitchen.makeFood(order);
        return delivery.deliverFood(food,address);
    }
    public int makeReservationGetID(){
        String order = frontDesk.makeReservation();
        if(order ==null) return -1;
        kitchen.prepare(order);
        return Integer.parseInt(order.substring(0,2));
    }
    public void cancelReservation(){
        int reserveID = frontDesk.cancelReservation();
        kitchen.cancel(reserveID);
    }
}
