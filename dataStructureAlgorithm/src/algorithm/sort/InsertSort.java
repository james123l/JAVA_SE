package algorithm.sort;

public interface InsertSort {
    /**
     * 实现思想：  解决冒泡排序的交换模式带来的大量开销 相比不断地交换 插入排序一次插入位置
     * 假定传入数组是一个无序表和一个有序表 有序表只有arr的第一个元素 而无序表只有arr的从index=1 开始的元素
     * 从无序表第一个开始向有序表按照位置插入元素
     */
    static boolean insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        //遍历无序表
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            // insertVal 待插入的下标 就是 insertVal前面的数的下标
            insertIndex = i - 1;
            /* 给insertVal 找到插入的位置
              insertIndex >= 0 保证在给insertVal 找插入位置，不越界
              insertVal < arr[insertIndex] 对比当前insertVal 和占据了insertIndex的数的大小 如果insertVal较小 则 insertVal应当插入到insertIndex insertIndex源数据后移一个位置
              就需要将 arr[insertIndex] 后移
             */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];    //在inseretIndex的数据 后移一个位置
                insertIndex--;                              //自减 和 再上一个元素对比位置
            }
            // 当退出while循环时，说明插入的位置找到, 索引是insertIndex + 1
            //如果此时的索引和原来的位置不同 则需要赋值， 如果和原来的位置一致 则不需要（因为无序表第一个元素的索引就是i）
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        return true;
    }
}