package leetcode.algorithmProblems;

import java.util.Collections;

public class Reverse_integer07 {
    public int reverse(int x){
        int var = 0;
        try {
            var = x>0?Integer.parseInt(String.valueOf(new StringBuffer(String.valueOf(x)).reverse())):(-1)*Integer.parseInt(String.valueOf(new StringBuffer(String.valueOf(x*(-1))).reverse()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return var;
    }

//    public static void main(String[] args) {
//        reverse_integer07 reverseInteger07 = new reverse_integer07();
//        System.out.println(reverseInteger07.reverse(100));
//        System.out.println(reverseInteger07.reverse(1534236469));
//        System.out.println(reverseInteger07.reverse(-100));
//    }

}
