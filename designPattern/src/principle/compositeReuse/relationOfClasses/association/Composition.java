package principle.compositeReuse.relationOfClasses.association;

/*
* 聚合和组合的区别：
* 聚合是个体离开了整体，依然可以存在.
*  组合是个体和整体不可以分开，个体不能离开整体单独存在.
* */
public class Composition {
}
class Room{
    public Room createRoom(){
        System.out.println("创建房间");
        return new Room() ;
    }
}

class House{
    private Room room ;
    public House(){
        room = new Room() ;
    }

    public void createHouse(){
        room.createRoom() ;

    }
}
