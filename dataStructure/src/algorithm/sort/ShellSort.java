package algorithm.sort;

public interface ShellSort {
    /**
    *希尔排序的思想 ： 解决插入排序问题 如果索引靠后的元素比较小 则需要多次对比。 所以希尔排序可以让前面的元素和后面对比 从而减少索引靠后效率低下的问题
    *根据数组长度给数组分组对比 例如数组长度是11 第一次分组 把数组分成11/2=5组 即索引为0的元素和索引为5的元素进行对比 直到第一轮结束
    *把之前的5组再 5/2 得到需要分为2组 则 0 2 4 6 8 10 ， 1 3 5 7 9 11 进行排序
    *2/2 = 1 一组进行排序
     */
    //交换法希尔排序 没有解决冒泡排序的开销问题
    static boolean shellSortByChange(int[] arr){
        int temp = 0;
        int count = 0;
        //外循环计算步长，直到步长为0时退出循环 步长即是分多少组， 每加一次步长 就是跳入下一个组继续计算
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 这里一共有gap个组 所以留出一个组的长度 从组内第二个数字开始 所以这里是i = gap
            for (int i = gap; i < arr.length; i++) {
                //对比i对应数组内位置的前面的元素和i这里的元素 然后依次向前走到数组头 则把组内最小数排序到组的头
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，需要交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }
        return true;
    }

    //移位法  --对交换式的希尔排序进行优化 解决了频繁交换产生的开销
    static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                //如果组内前一个元素大于当前元素
                if (arr[j] < arr[j - gap]) {
                    //插入排序
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }

            }
        }
    }

}
