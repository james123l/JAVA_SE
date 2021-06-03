
package classes.enums.java5;
public class DefinedEnum {
    public static void main(String[] args) {
        //调用的时候有4种返回值可选
        Season fall = Season.fall;
        System.out.println(fall);

        //Enum类的常用方法
        //values获取所有的内部元素并返回数组
        Season[] seasons = Season.values();
        for (Season s:seasons) {
            //Enum类默认打印对象名
            System.out.println(s.toString());
        }
        //valueof返回枚举类内部元素名与字符串相同的元素 没找到会抛出IllegalArguementException
        Season winter = Season.valueOf("winter");
        System.out.println(winter);

    }

}
enum Season{
    //默认继承java.lang.Enum
    //可选择的选项需要先写出来,逗号间隔
    //默认为public static final 修饰
    spring ("spring"),
    Summer ("summer"),
    fall ("fall"),
    winter("winter");

    //唯一获得信息的方法
    public String getName() {
        return name;
    }

    //枚举类
    private String name;
    //私有化构造器
    private  Season(String name) {
        this.name = name;
    }
}
