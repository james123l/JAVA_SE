package structural.facade;

public class Client {
    public static void main(String[] args) {
        FacadeRestaurant restaurant = new FacadeRestaurant();
        int i = restaurant.makeReservationGetID();
        System.out.println("reservation ID :"+i);
        restaurant.cancelReservation();
        System.out.println(restaurant.placeOrder());

    }
}
