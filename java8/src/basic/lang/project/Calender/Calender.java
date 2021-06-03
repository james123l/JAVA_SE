package basic.lang.project.Calender;


import java.util.Scanner;


public class Calender {
    //设定日期全局变量
    //static 方法只能调用static对象 所以全局变量要用static
    public static  int[] date = {Integer.MIN_VALUE,Integer.MIN_VALUE};
    public static int[] day= {31,28,31,30,31,30,31,31,30,31,30,31};



    public static void main(String[] args) {
        //1. 输入年月
        //2.计算1900-1-1到现在总天数 例如输入2017-7 则求到2017-7-1
        //3.根据当前是星期几 打印月历
        printCalender();
    }



    private static void printCalender() {
        //priviate 代表只能在这个class文件内调用 是改class的私有方法inputDate();
        inputDate();
        printCalender(sumDay());
    }


    //1.输入日期字符串
    private static void inputDate(){
        while(true) {
            System.out.println("input a date like 1900-1（1900-5000）:");
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            String[] tempstr;
            String delimeter = "-";  // 指定分割字符， （. 号需要转义）
            tempstr = str.split(delimeter); // 分割字符串
            for (int i = 0; i < tempstr.length; i++) {
                date[i] = Integer.parseInt(tempstr[i]);
            }
            if( !(validateMonth(date[1])&&validateYear(date[0]))){
                //节约内存 注销对象
                input.close();
                input=null;
                break;
            }
            System.out.println("Month must between 1-12!!!");
        }
        System.out.println(date[0]+"-"+date[1]+" calender is following：");
    }
    private static boolean  validateMonth(int mon){
        return (mon<0|| mon>12);
    }
    private static boolean  validateYear(int year){
        return (year>=1900&&year<=5000);
    }


    //2.获得1900-1到日期的总天数
    //因为日期是全局变量 所以不需要传参
    private static int sumDay(){
        int sum= sumDayFrom1900()+sumDayToMonth();
        return sum;
    }
    private static int sumDayFrom1900(){
        int sum=0;
        //java不初始化会报错
        for (int i = 1900; i < date[0]; i++) {
            sum+= 365;
            if (leapYear(i)) {
                sum++;
            }
        }
        return sum;
    }
    private static int sumDayToMonth(){
        int sum=0;
        if (leapYear(date[0])) {
            day[1]=29;
        }
        for (int i = 0; i < date[1]-1; i++) {
            sum+=day[i];
        }
        return sum;
    }
    private static boolean leapYear(int year){
        return (year%400== 0 || year%4==0&&year%100!=0);
    }



    //3.打印月历
    public static void printCalender(int days) {
        /** input number to show the calender of day count from 1900-1*/
        String[] weekday= { "Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        for (int i = 0; i <7 ; i++) {
            System.out.print(weekday[i]+'\t');
        }
        System.out.println("");
        int week = days%7;
        for (int i = 1; i <=week+day[date[1]-1]; i++) {
            if(i<=week)  System.out.print("\t");
            else{
                if(i%7==0)  System.out.print((i-week)+"\n");
                else  System.out.print((i-week)+"\t");
            }
        }
    }
}
