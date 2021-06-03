
package classes.enums.java4;
public class DefinedEnum {
    public static void main(String[] args) {
        //调用的时候有4种返回值可选
        Season fall = Season.fall;
        System.out.println(fall);
    }

}
class Season{
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
    //可选择的选项
    public static final Season spring = new Season("spring");
    public static final Season Summer = new Season("summer");
    public static final Season fall = new Season("fall");
    public static final Season winter = new Season("winter");


    @Override
    public String toString() {
        return this.name;
    }
}
