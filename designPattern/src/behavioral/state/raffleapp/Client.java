package behavioral.state.raffleapp;

public class Client {
    public static void main(String[] args) {
        RaffleActivity raffleActivity = new RaffleActivity(10);
        for (int i = 0; i < 500; i++) {
            if(raffleActivity.getState()==raffleActivity.getOutOfPrize()) break;
            raffleActivity.reduceCredits();
            raffleActivity.raffle();
        }
    }
}
