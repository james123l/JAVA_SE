package classes.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SimpleDateFormateCalender {
    public static void main(String[] args) {
        Date date = new Date();
        try {
            calenderTest();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    //测试两种日期类型
    public static void testTwoDates(){
        //父类是java.util.date 子类是java.sql.date
        //tostring 显示为年月日时分秒
        java.util.Date datenow = new Date();    //空参是当前时间
        java.util.Date dateutil = new Date(4899498854444l);
        System.out.println(dateutil);
        java.sql.Date datesql = new java.sql.Date(4899498854444l);
        System.out.println(datesql);
        //相互转换
        //gettime显示毫秒数
        java.util.Date dateutilTemp = new Date(datesql.getTime());
    }
    //测试simpledateformate的方法
    public static void testSimpleDateFormat(Date date) throws ParseException {
        //空参
        SimpleDateFormat simpleDateFormatNoParameter = new SimpleDateFormat();
        //pattern参数
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //格式化 日期 -》string
        String formatted = simpleDateFormat.format(date);
        System.out.println(formatted);
        //解析 string -》 date
        Date parsed = simpleDateFormat.parse(formatted);

    }
    //测试java.util.GregorianCalendar
    public static void calenderTest() throws ParseException {
        //抽象类Calender 由子类GregorianCalendar来实现
        //单例对象
        //这个实例实际上是class java.util.GregorianCalendar  它的父类是Calender
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getClass());
        System.out.println(cal.getClass().getSuperclass());

        //获得当前对象时间
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

        // 设定时间 set
        //cal.set(cal.DAY_OF_MONTH,22); //当前日期改为22号 也可以改变以上的get函数的参数
        cal.set(2012,5,12,14,28,4);
        cal.set(Calendar.DAY_OF_MONTH,8);
        //Calendar 类型的getTime函数 返回值是Date类型
        Date date = cal.getTime();
        System.out.println(date.toLocaleString());

        //当前年份加三年
        cal.add(Calendar.YEAR,3);
        System.out.println(cal.get(Calendar.YEAR));

        //gettime settime
        Date time1 = cal.getTime();
        cal.setTime(time1);
        System.out.println(date);

        //simple date formate
        // 格式化日期类
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
    }
    //对localDate localtime localdatetime 测试
    public static void testLocal(){
        //now
        LocalDate localDate =  LocalDate.now();
        LocalTime localTime =  LocalTime.now();
        LocalDateTime localDateTime =  LocalDateTime.now();
        //of
        LocalDateTime localDateTime1 = LocalDateTime.of(2012, 5, 12, 14, 28, 4);
        System.out.println(localDateTime1);
        //get
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println(dayOfWeek);
        //with  与calender可变不同 localDateTime不可变 只返回需要的返回值
        LocalDateTime localDateTime2 = localDateTime.withYear(2021);
        LocalDateTime localDateTime3 = localDateTime.plusYears(12);
        System.out.println(localDateTime);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);
    }
    //测试instant  用处小
    public void testInstant(){
        Instant now = Instant.now();
        System.out.println(now); //2021-02-02T 07:29:41.719Z
        //添加时间偏移
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

    }
}
