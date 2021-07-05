package creational.builder;

public class Client {
    public static void main(String[] args) {

        //盖普通房子
        House commonHouse = new House();
        //准备创建房子的指挥者
        Director director = new Director(commonHouse);

        //完成盖房子，返回产品(普通房子)
        Construction house = director.construct();

        //System.out.println("输出流程");

        System.out.println("--------------------------");
        //盖高楼
        Skycrapper skycrapper = new Skycrapper();
        //重置建造者
        director.setBuilder(skycrapper);
        //完成盖房子，返回产品(高楼)
        director.construct();
    }
}
