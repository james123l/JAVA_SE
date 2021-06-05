package algorithm.sort;

public interface BubbleSort {
    static boolean bubbleSort(int[] arr){
        // 冒泡排序 的时间复杂度 O(n^2)
        /**
         * 实现思想：
         * 两两对比 如果第一个大于第二个 则交换位置 这样进行到数组最后一个元素的时候可以把最大的元素放到最后一个
         * 再循环 两两对比 对比到倒数第二个 这样倒数第二大的数就排在倒数第二个位置
         */
        int temp = 0; // 临时变量
        boolean flag = false; // 标识变量，表示是否进行过交换, 如果本次循环没有任何元素进行交换 则说明剩下的元素是有序的 可以直接退出循环
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag!!!, 进行下次判断
            }
        }
        return true;
    }
}
