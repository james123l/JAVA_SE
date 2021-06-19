package generic;

public class BoxingAndUnboxing {
    public static void main(String[] args) {
        double num= 3.14;

        //Boxing
        //2种装箱示例
        //Double boxed_num1 = new Double(3.14);   //这里输入3.14或者"3.14"字符串 都可以
        Double boxed_num1 = Double.valueOf(3.14);
        Double boxed_num2 = Double.valueOf(num);
        //自动装箱
        Double boxed_num3 = num;  // 也可以等于3.14
        System.out.println(boxed_num1+"\t"+boxed_num2+"\t"+boxed_num3+"\t");


        //Unboxing
        //方法拆箱
        num = boxed_num1.doubleValue();
        System.out.println(boxed_num1+"\t");
        num = boxed_num1;
        System.out.println(boxed_num1+"\t");

        //Integer的缓存对象
        //对Integer.valueOf的分析
        /*
        @HotSpotIntrinsicCandidate
        public static Integer valueOf(int i) {
            if (i >= Integer.IntegerCache.low && i <= Integer.IntegerCache.high)
                return Integer.IntegerCache.cache[i + (-Integer.IntegerCache.low)];
            return new Integer(i);
        }
        参数为int的valueof函数底层实现 版本高于java 1.5
        由此可以看出 当value在-128-127之间的时候 返回值是cache对象 所以innum=100的测试返回值是true 200的则是false
         */
        Integer innum1 = 100;
        Integer innum2 = 100;
        //等于 Integer innum1 = Integer.valueOf(100);
        System.out.println(innum1==innum2);
        Integer.valueOf(100);

        Integer innum3 = 200;
        Integer innum4 = 200;
        //因为大于128 所以等于new了一个新的对象
        System.out.println(innum3==innum4);
        // compareTo函数 innum3>innum4 则返回1 小于返回-1 相等返回0
        System.out.println(innum3.compareTo(innum4));

        //charactor包装类
        Character.isDigit('a');
        Character.isLetterOrDigit('a');
        Character.isUpperCase('a');
        Character.isJavaIdentifierPart('a'); //判断除了首字母以外的其他字母是否符合Java变量命名规则
        Character.isJavaIdentifierStart('a');  //判断除了首字母是否符合Java变量命名规则

        //String类型也可以有valueOf函数 String.valueOf(包装类/基本数据类型) 可以把基本数据类型等或者Integer等包装类转换为字符串
        //上述字符串转换为int类型可以使用Integer.parseInt()， 如果字符串内不是数字却要转换为int 可能出现numberformat exception异常
    }
}
