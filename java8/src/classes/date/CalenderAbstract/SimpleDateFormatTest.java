package classes.date.CalenderAbstract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SimpleDateFormatTest {
    public static void main(String[] args) {
        Date date = new Date();
        try {
            calenderTest();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public static void testSimpleDateFormat(Date date) throws ParseException {
        //空参
        SimpleDateFormat simpleDateFormatNoParameter = new SimpleDateFormat();
        //pattern参数
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //格式化
        String formatted = simpleDateFormat.format(date);
        System.out.println(formatted);
        //解析
        Date parsed = simpleDateFormat.parse(formatted);

    }
    public static void calenderTest() throws ParseException {
        //抽象类Calender 多由子类GregorianCalendar来实现
        //是个单例对象
        Calendar cal = Calendar.getInstance();
        //这个实例实际上是class java.util.GregorianCalendar  它的父类是Calender
        System.out.println(cal.getClass());
        System.out.println(cal.getClass().getSuperclass());

        //获得当前时间
        int year= cal.get(Calendar.YEAR);
        int month= cal.get(Calendar.MONTH)+1;
        int day= cal.get(Calendar.DATE);
        //int hour= cal.get(Calendar.HOUR);
        int hour= cal.get(Calendar.HOUR_OF_DAY);  //24小时制
        int minute= cal.get(Calendar.MINUTE);
        int second= cal.get(Calendar.SECOND);
        int millisecond= cal.get(Calendar.MILLISECOND);
        String time=String.format("%d-%02d-%02d %d-%d-%d%s",year,month,day,hour,minute,second,millisecond);
        System.out.println(time);

        // 设定时间
        //cal.set(cal.DAY_OF_MONTH,22); //当前日期改为22号 也可以改变以上的get函数的参数
        cal.set(2012,5,12,14,28,4);
        //Calendar 类型的getTime函数 返回值是Date类型
        Date date = cal.getTime();
        System.out.println(date.toLocaleString());

        //格式化日期类
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hhmmsssss");
        time = format.format(date);
        System.out.println(time);

        //字符串转化为Date类型
        date = format.parse("2021-12-31 235959999");
        System.out.println(date.toLocaleString());

        //GregorianCalender类 cal是calendar类 此处为多态
        Calendar cal2 = new GregorianCalendar(2021,1,1);
        //绝对值
        long dif = Math.abs( cal2.getTimeInMillis()-cal.getTimeInMillis());
        int difDay = (int)(dif%1000*60*60*24);
        System.out.println("相差"+difDay+"天");

        //当前年份加三年
        cal.add(Calendar.YEAR,3);
        System.out.println(cal.get(Calendar.YEAR));
    }
}
