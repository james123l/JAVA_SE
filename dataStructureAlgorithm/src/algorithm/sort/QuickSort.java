package algorithm.sort;

public interface QuickSort {
    /**
    *快速排序的思路： 是对冒泡排序的优化
    *选择数组的中轴 通过左边的值如果大于中轴 就和右边小于中轴的值进行交换 假设中轴是0
    *1.当左右两个指针都没有到达 0 ，左边都是负数，右边都是正数
    *2.如果一个指针先到达中轴 那么这么指针则一直指向中轴 假设右指针指向中轴 左边还有 -1，2，1，0 那么-1 不和0交换 ，2和0交换后中轴变为2 ，1则不和2交换。
    *3. 左指针++到中轴右边一个元素 右指针--到中轴左边一个元素 如果指针指向数组头或者尾 则不需要继续运算 否则递归继续快排
     */
    static void quickSortRecursion(int[] arr){
        quickSortRecursion(arr,0,arr.length-1);
    }
    static void quickSortRecursion(int[] arr, int left, int right) {
        int tempLeft = left; //左下标
        int tempRight = right; //右下标
        //pivot 中轴的值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        //while循环的目的：把比中轴值小的放到左边 大的放到右边
        while( tempLeft < tempRight) {
            //从两边朝着中间寻找
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while( arr[tempLeft] < pivot) {
                tempLeft += 1;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while(arr[tempRight] > pivot) {
                tempRight -= 1;
            }
            //如果tempLeft >= tempRight： pivot左边都小于pivot pivot右边都大于pivot
            if( tempLeft >= tempRight) {
                break;
            }
            //交换
            temp = arr[tempLeft];
            arr[tempLeft] = arr[tempRight];
            arr[tempRight] = temp;

            //如果交换到中轴 则不继续更新 把每次更新的量切换回中轴
            if(arr[tempLeft] == pivot) {
                //tempRight--的原因：tempLeft已经指向了中轴值之后原来中轴的指针是tempRight
                tempRight -= 1;
            }
            if(arr[tempRight] == pivot) {
                tempLeft += 1;
            }
        }
        // 如果 tempLeft == tempRight, 必须tempLeft++, tempRight--, 否则为出现栈溢出
        // 此时中轴的定位已经确定 需要错开中轴 因为两个指针不能跨越中轴 如果相等 那么他们就一定等于中轴下标
        if (tempLeft == tempRight) {
            tempLeft += 1;
            tempRight -= 1;
        }
        //向左递归
        if(left < tempRight) {
            quickSortRecursion(arr, left, tempRight);
        }
        //向右递归
        if(right > tempLeft) {
            quickSortRecursion(arr, tempLeft, right);
        }


    }
}
