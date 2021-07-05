package creational.builder;

/*
* Product 产品类
* 具体参数交给builder实现类进行赋值
* */
public class Construction {
    private int base;
    private int wall;
    private int roof;
    public int getBase() {
        return base;
    }
    public void setBase(int base) {
        this.base = base;
    }
    public int getWall() {
        return wall;
    }
    public void setWall(int wall) {
        this.wall = wall;
    }
    public int getRoof() {
        return roof;
    }
    public void setRoof(int roof) {
        this.roof = roof;
    }
}
