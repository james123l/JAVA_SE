package creational.builder;


public class Skycrapper extends Builder {

    @Override
    public void buildBase() {
        this.construction.setBase(100);
    }

    @Override
    public void buildWalls() {
        this.construction.setWall(20);
    }

    @Override
    public void roof() {
        this.construction.setRoof(100);
    }

}