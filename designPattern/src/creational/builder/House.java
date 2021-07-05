package creational.builder;

public class House extends Builder {

    @Override
    public void buildBase() {
        this.construction.setBase(5);
    }

    @Override
    public void buildWalls() {
        this.construction.setWall(10);
    }

    @Override
    public void roof() {
        this.construction.setRoof(10);
    }

}