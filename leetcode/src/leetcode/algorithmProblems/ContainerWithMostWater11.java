package leetcode.algorithmProblems;

public class ContainerWithMostWater11 {
    public int maxArea(int[] height) {
        int max = 0;
        int start = 0, end = height.length - 1;
        while (start < end) {
            //通过多次外部循环 更新最小高度
            //取出当前循环 左右两个指针最小高度
            int h = Math.min(height[start], height[end]);
            //对比出最大的面积
            max = Math.max(max, h* (end - start));
            while(start<end && height[start] <=h) start++;
            while(start<end && height[end]<=h) end--;
        }
        return max;
    }
}
