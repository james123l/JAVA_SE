package leetcode.twoPointer;

public class N27RemoveElement {
    //标准解法
    public int removeElement(int[] nums, int val)  {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
    //有点啰嗦 双指针
    public int removeElementMySolution(int[] nums, int val) {
        int front = -1 , end = nums.length-1;
        while(true){
            if(end < 0){
                return 0;
            }else if(nums[end] == val){
                end--;
            }else{
                break;
            }
        }
        OUT:while(true) {
            front++;
            if (front > end) break OUT;
            //end指针指向非val数字
            while (nums[front] == val) {
                nums[front] = nums[end];
                front++;
                do {
                    end--;
                    if (front > end) break OUT;
                } while (nums[end] == val);
            }
        }
        return front+1;
    }

}
