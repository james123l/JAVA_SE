package generic;

import java.util.ArrayList;
import java.util.List;

/*
* 泛型的通配符操作
* */
public class WildCardGeneric {
    public static void main(String[] args) {
        testWildCardGenericMethod();
    }
    public static void testWildCardGenericMethod(){
        List<?> list = new ArrayList<>();
        List<Integer> nums = new ArrayList<>(); nums.add(1);
        //add 使用通配符的集合只能添加null
        list = nums;
        list.add(null);
        //get
        Object obj = list.get(1);
        System.out.println(obj);
    }
    public static void testLimitedWildCard(){
        List<Object> objs = new ArrayList<>();
        List<Person> person = new ArrayList<>();
        List<SubPerson1> subPerson = new ArrayList<>();

        List<? super Person> listsuper = new ArrayList<>(); //可以匹配Person及其父类
        List<? extends Person> listextends = new ArrayList<>();//可以匹配Person及其子类
        //super
        listsuper = person;
        listsuper = objs;
//        listsuper = subPerson; 报错
        //get函数用obj接受
        Object object = listsuper.get(0);
        //可以添加person的子类
        listsuper.add(new SubPerson1(1,1,1));


        //extends
//        listextends = objs; 报错
        listextends = person;
        listextends = subPerson;
        //get函数用person接受
        Person person1 = listextends.get(0);
        //不能添加
//        listextends.add(new Person(1,1,1));
    }
}
