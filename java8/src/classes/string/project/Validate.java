package  classes.string.project;

public class Validate {
    // 第33～126号(共94个)是字符，其中第48～57号为0～9十个阿拉伯数字；65～90号为26个大写英文字母，97～122号为26个小写英文字母，其余为一些标点符号、运算符号等。

    //封装方法
    //检测输入字符是否是特殊字符
    private static boolean detectSpecialChar(char ch){
        System.out.println("detecting SpecialChar");
        return (int)ch<48&& (int)ch>32 || (int)ch<126&&(int)ch>122 ||(int)ch<97&&(int)ch>90;
    }
    //检测字符是否是数字
    private static boolean detectNumber(char ch){

        System.out.println("detecting is number....");
        return (int)ch>=48&& (int)ch<=57 ;
    }
    //检测字符是否是大写字母
    private static boolean detectUppercase(char ch){
        System.out.println("detecting is upper....");
        return (int)ch<91&& (int)ch>64;
    }
    //检测字符是否是小写字母
    private static boolean detectLowercase(char ch){
        System.out.println("detecting is lower....");
        return (int)ch<123&& (int)ch>96;
    }
    //检测字符串包含特殊符号 空格
    private static boolean stringWithSpecialChar(String str){
        System.out.println("detecting special char from string....");
        for (int i = 0; i < str.length();i++) {
                if(detectSpecialChar(str.charAt(i))) return false;
            }
        return false;
    }
    //检测字符串内是否一个符号仅出现一次
    //从头到尾查和从尾到头查index是否一致
    private static boolean appearOnce(String str,char ch){
        System.out.println("detecting @/.");
        return str.indexOf(ch)==str.lastIndexOf(ch);
    }


    //Username
    //用户名在8-25位 不能包含特殊符号 空格
    public static int validateUsername(String username){
        System.out.println("username validating...");
        System.out.println(username);
        if(username.length()>25|| username.length()<8){
            System.out.println("username should between 8-25");
            return 0;
        }
        else if( stringWithSpecialChar(username)){
            System.out.println("username should not contain special charactor");
            return 1;
        }
        else {
            System.out.println("legal username");
            return 2;
        }
    }


    //Password
    //密码在6-18位 只能包含字母 数字 特殊符号
    //        纯数字/字母 密码强度为弱
    //        两种搭配为中等
    //        三种搭配为强
    public static int validatePassword(String password){
        System.out.println("password validating...");
        if (passwordLegalLength(password)){
            int[] flag= new int[3];
            int stength=0;
            for (char ch: password.toCharArray()) {
                if (detectSpecialChar(ch)){
                    flag[0]=1;
                }
                else if (detectUppercase(ch)||detectLowercase(ch)){
                    flag[1]=1;
                }
                else if (detectNumber(ch)){
                    flag[2]=1;
                }
                else
                    return 4;
            }
            for (int i : flag) {
                stength+=flag[i];
            }
            switch (stength){
                case 1:
                    System.out.println("password:weak"); return 1;
                case 2:
                    System.out.println("password:middle"); return 2;
                case 3:
                    System.out.println("password:strong"); return 3;
            }
        }
        return 0;
    }
    //判断位数是否在6-18
    private static boolean passwordLegalLength(String password){
        System.out.println("password is between 6-18...");
        return password.length()>6&& password.length()<18;
    }


    //Email
    //用户需要输入正确的email 仅有一个@和. 符号不能开头不能结尾
    public static int validateEmail(String email ){
        System.out.println("email validating...");
        if(detectSpecialChar(email.charAt(0))) {
            System.out.println("email start with special charactor...");
            return 0;
        }
        if(appearOnce(email,'@')&&appearOnce(email,'.')) {
            System.out.println("email contain more than one @/. ");
            return 1;
        }
        return 2;
   }
}