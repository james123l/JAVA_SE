package behavioral.observer;

/*
* 观察者管理接口
* */
public interface ObserverManager {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
