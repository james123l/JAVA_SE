package algorithm.sort;

public interface SimpleSelectSort {
    /**
     * 选择排序的实现思想
     *一个数组 8 5 4 9 3 6
     *第一轮循环 先假设index = 0的数是最小的 依次和后面进行对比 如果当前的数index = x比index = 0小 则和这两个数字交换位置 ，第一轮对比结束后会把最小的数字转到第一个
     *第二轮从index = 1 的数开始继续对比
     */
    //选择排序时间复杂度是 O(n^2)
    static boolean simpleSelectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;       //最小值的索引
            int min = arr[i];       //最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {     // 说明假定的最小值，并不是最小
                    min = arr[j];       // 重置min
                    minIndex = j;       // 重置minIndex
                }
            }
            // 将最小值，放在arr[0], 即交换
            if (minIndex != i) {        //判断是否发生过交换
                arr[minIndex] = arr[i]; //把假定的最小值赋值给实际的最小值
                arr[i] = min;           //把实际的最小值赋值给index = i
            }
        }
        return true;
    }
}
