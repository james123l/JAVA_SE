package behavioral.observer;

public class CurrentCondition implements Observer {
    private float temperature;
    private float pressure;
    private float humidity;

    // 更新天气情况由 WeatherData 来调用，使用推送模式
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("===Current Condition====");
        System.out.println("temperature : " + temperature + " c");
        System.out.println("pressure: " + pressure + " p");
        System.out.println("humidity: " + humidity + " %");
    }
}
