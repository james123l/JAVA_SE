package classes.compare;
import java.util.Arrays;
import java.util.Comparator;

public class Compare {
    public static void main(String[] args) {
        Mouse[] mouse = new Mouse[5];
        mouse[0] = new Mouse("asus",10);
        mouse[1] = new Mouse("microsoft",20);
        mouse[2] = new Mouse("huawei",30);
        mouse[3] = new Mouse("dell",30);
        mouse[4] = new Mouse("ibuypower",40);
        show(mouse);
        //匿名方法实现comparator
        //返回值决定比较方式 和comparable一致
        Arrays.sort(mouse, new Comparator<Mouse>() {
            @Override
            public int compare(Mouse o1, Mouse o2) {
                if(o2 instanceof  Mouse&& o1 instanceof Mouse){
                    Mouse origional = (Mouse) o2;
                    Mouse mouse = (Mouse) o2;
                    if (origional.price>mouse.price) return 1;
                    if (origional.price<mouse.price) return -1;
                        //默认由低到高，所以加符号改变顺序
                    else return -origional.name.compareTo(mouse.name);
                }
                throw new RuntimeException("cast exception");
            }
        });

    }
    public static void show(Mouse[] mouse){
        Arrays.sort(mouse);
        System.out.println(Arrays.toString(mouse));
    }
    public static Comparator comparatorGetter(){
        Comparator<Mouse> comparator = new Comparator<Mouse>() {
            @Override
            public int compare(Mouse o1, Mouse o2) {
                if (o2 instanceof Mouse && o1 instanceof Mouse) {
                    Mouse origional = (Mouse) o2;
                    Mouse mouse = (Mouse) o2;
                    if (origional.price > mouse.price) return 1;
                    if (origional.price < mouse.price) return -1;
                        //默认由低到高，所以加符号改变顺序
                    else return -origional.name.compareTo(mouse.name);
                }
                throw new RuntimeException("cast exception");
            }
        };
        return comparator;
    }
}
//继承comparable
class Mouse implements Comparable{
    String name;
    int price;
    Mouse(String _name,int _price){
        this.name = _name;
        this.price=_price;
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    /*自然排序 重写compareTo方法 进行由低到高的排序
        返回值>0 this>object
        返回值<0 this<object
        返回值=0 this=object
         */
    //二级排序示例 先按照价格，如果价格一致 按照名称从高到低
    @Override
    public int compareTo(Object o) {
        if(o instanceof  Mouse){
            Mouse mouse = (Mouse) o;
            if (this.price>mouse.price) return 1;
            if (this.price<mouse.price) return -1;
            //默认由低到高，所以加符号改变顺序
            else return -this.name.compareTo(mouse.name);
        }
        throw new RuntimeException("你输入这啥玩意？");
    }
}
