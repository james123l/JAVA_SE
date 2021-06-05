package leetcode.algorithmProblems;

import java.util.Arrays;

public class LongestCommonPrefix14 {
    //暴力破解
    public String longestCommonPrefix01(String[] strs) {
        //求最短字符串
        String shortStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if(strs[i].length()<shortStr.length()) shortStr=strs[i];
        }
        //暴力破解
        int i;
        OUT:for ( i = 0; i < shortStr.length(); i++) {
            for (int j = 0; j < strs.length;j++){
                if(strs[j].charAt(i)!=shortStr.charAt(i)) {
                    break OUT;
                }
            }
        }
        //截取[0,i）区间 所以没有i--
        return i==0?"":shortStr.substring(0,i);
    }

    //排序算法,排序后只需要对比第一个和最后一个，因为他们差别最大。
    public String longestCommonPrefix02(String[] strs) {
        //排序
        Arrays.sort(strs);
        /*
        //默认排序顺序是字母对比 短串更小，字母越小 代表越小。
        for (String str: strs) {
            System.out.println(str);
        }
         */
        //排序后只需要对比第一个和最后一个的最大prefix
        int i = 0;
        int length =Math.min(strs[0].length(), strs[strs.length-1].length());
        for(i=0;i<length;i++){
            if(strs[0].charAt(i)!=strs[strs.length-1].charAt(i)){
                break;
            }
        }
        return i==0?"":strs[0].substring(0,i);
    }

    public static void main(String[] args) {
        LongestCommonPrefix14 longestCommonPrefix14 = new LongestCommonPrefix14();
        System.out.println(longestCommonPrefix14.longestCommonPrefix02(new String[]{"flower", "flow", "flight"}));
    }

}
