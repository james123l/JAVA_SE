package leetcode.algorithmProblems;

import java.util.Arrays;

public class RemoveDuplicatesfromSortedArray26 {
    //暴力算法
    public int myRemoveDuplicates(int[] nums) {
        int length = 1;
        if(nums.length==0) return 0;
        if(nums.length==1) return 1;
        //获取数组中最大值
        int max = nums[nums.length-1];
        int tempIndex=0;
        for(int i = 1; i < nums.length; i++){
            if(nums[tempIndex]!=nums[i]){
                tempIndex=i;
                length++;
            }else{
                nums[i]=max;
            }
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length;i++){
            if(nums[i]==max){
                tempIndex = i;
                break;
            }
        }
        return tempIndex+1;
    }
    //双指针
    //i指针在原来的数组从头写入不重复的数字， j指针遍历数组
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
