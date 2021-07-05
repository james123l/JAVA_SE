package structural.facade;

import java.util.Scanner;

public enum FrontDesk {
    FRONT_DESK;

    private int reserved;

    public String getOrder(){
        System.out.println("food:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public String getAddress(){
        System.out.println("address:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String makeReservation(){
        if(reserved<20){
            reserved++;
            System.out.println("reserve food:");
            Scanner scanner = new Scanner(System.in);
            return reserved>10?"":"0"+String.valueOf(reserved) + scanner.nextLine().toString();
        }else{
            return null;
        }
    }
    public int cancelReservation(){
        reserved--;
        System.out.println("reserved number:");
        Scanner scanner = new Scanner(System.in);
        return  scanner.nextInt();
    }

}
