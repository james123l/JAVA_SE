package behavioral.observer;

/*
* 观察者接口
* */
public interface Observer {
    void update(float temperature, float pressure, float humidity);
}
