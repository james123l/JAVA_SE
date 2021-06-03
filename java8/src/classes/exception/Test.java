package classes.exception;

import java.nio.channels.ScatteringByteChannel;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Scanner 类不安全 input.nextint的异常处理在内部实现 所以出现异常后程序会自动关闭 所以尽可能不在开发的时候用
        int age = -1;
        try{
            age = input.nextInt();
        }catch(InputMismatchException | ArithmeticException e){
            //多重catch
            //multi-catch feature在java7之后才有
            e.printStackTrace();
        }catch(Exception e){
            //getmessage有的是null。所以常用堆栈打印
            e.printStackTrace();
            //System.err.println(e.getMessage());
        }finally {
            //不论是否异常都会执行，如果是系统级异常 程序退出 finally外部的代码不会被执行，内部的才会被执行。 
	    //如果是在方法体内部 try里面即便写了return finally也会在return之前被执行
            System.out.println("年龄输入结束！");
        }
        try{
            division("123","abc");
        }catch(ArithmeticException|NumberFormatException e){
            e.printStackTrace();
        }
        System.out.println("程序结束！");

    }
    public static void division(String str1,String str2) throws ArithmeticException,NumberFormatException {
        //可以抛出一个或多个异常
        //抛出异常 谁调用 谁处理
        int num1 = Integer.parseInt(str1);
        int num2 = Integer.parseInt(str2);
        int result = num1 / num2 ;
        System.out.println(result);
    }

}
