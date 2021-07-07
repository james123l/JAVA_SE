package behavioral.observer;

public class Client {
    public static void main(String[] args) {
        //创建一个WeatherData
        WeatherData weatherData = new WeatherData();

        //创建观察者
        CurrentCondition currentCondition = new CurrentCondition();
        ApplicationWebsite webSite = new ApplicationWebsite();

        //注册到weatherData
        weatherData.registerObserver(currentCondition);
        weatherData.registerObserver(webSite);

        //测试
        weatherData.setData(10f, 100f, 30.3f);


        weatherData.removeObserver(currentCondition);
        //测试
        System.out.println();
        weatherData.setData(10f, 100f, 30.3f);
    }
}
