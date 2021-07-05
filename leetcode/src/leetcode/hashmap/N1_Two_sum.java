package leetcode.hashmap;

import java.util.*;

public class N1_Two_sum {
    /*
    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    You can return the answer in any order.
     */
    //O(n*n)暴力破解
    public static int[] solution01(int[] nums, int target){
        int[] solution = new int[2];
        for(int i = 0; i < nums.length;i++){
            int diff = target - nums[i];
            for(int j = i+1;j<nums.length;j++){
                if(diff==nums[j]) {
                    solution[0]=i;
                    solution[1]=j;
                }
            }
        }
        return solution;
    }
    //O(n)
    /*
    使用hashMap储存key value
    当数组开始遍历时，先求出差，寻找map中是否存在这个差。
    一开始数组是空的，会存储num[i],i作为entry进入map，随后的map逐渐增加，会把新的差和已经存在的key对比，如果存在则取出value。
     */
    public static int[] solution02(int[] nums, int target){
        int[] solution = new int[2];
        int diff;
        HashMap<Integer,Integer> hashMap = new HashMap();
        for(int i = 0; i < nums.length;i++){
            diff = target - nums[i];
            if(hashMap.containsKey(diff)){
                solution[0]=hashMap.get(diff);
                solution[1]=i;
            }
            hashMap.put(nums[i],i);
        }
        return solution;
    }

}
