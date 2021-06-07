package leetcode.algorithmProblems;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters3 {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("abcabcde" );
        System.out.println(lengthOfLongestSubstring("cdd"));
    }

    //暴力算法
    public static int lengthOfLongestSubstringBruteForce(String str) {
        if(str.length() == 0) return 0;
        //从头遍历 如果发现含有相同字符 找到index+1 重新计数
        int index = 0 , num ,max=1;
        StringBuffer buffer = new StringBuffer().append(str.charAt(index));
        String buff ="";
//        buff += str.charAt(index);
        for(int i = 1; i < str.length(); i++){
            if( (num =buffer.indexOf(buff =str.substring(i,i+1)))!=-1) {
                //说明有重复字符出现 num就是重复字符的下标 赋值给index
                index = num++;
                if(buffer.length()>max) max = buffer.length();
//                System.out.println(buffer.toString()+max);
                //删除是开区间
                buffer = buffer.delete(0,index+1).append(buff);
            }else{
                //没找到 buffer需要扩大长度
                buffer.append(buff);
            }
        }
        return Math.max(max,buffer.length());
    }
    //使用hashmap 去重
    public static int lengthOfLongestSubstring(String str){
        if(str.length() == 0) return 0;
        char[] charArray = str.toCharArray();
        int max = 0,index = 0,start = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put(charArray[0],0); //加入第一个
        for(int i = 1; i < charArray.length; i++){
            if(hashMap.containsKey(charArray[i])){
                //获取上一个该字母的下标
                index = hashMap.get(charArray[i]);
                //新的不重复子串的开始索引  并且和max比较
                index++;
                //移除重复字母出现的前面的元素
                for(int j = start; j < index; j++){
                    hashMap.remove(charArray[j]);
                }
                hashMap.put(charArray[i],i);
                start = index;
            }else{
                //hashmap中没有出现重复字母
                hashMap.put(charArray[i],i);
            }
            if(hashMap.size()>max){
                max = i-index+1;
            }
        }
        //判断 循环结束后如果hashmap储存的subarray长度是否大于max
        max = Math.max(hashMap.size(),max);
        return max;
    }
}
