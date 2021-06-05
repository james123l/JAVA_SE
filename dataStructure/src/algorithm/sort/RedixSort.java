package algorithm.sort;

public interface RedixSort {
    //空间换时间 桶排序的改进 需要大量的空间 不适合大量数据排序
    //

    /**
     * 算法解析：
     * 找到当前数组的最大数字 取它的位数
     * 设立10个桶 分别装位数上的数字 0-9 如果需要计算负数 则开辟为19个桶 但是耗费空间过大 可以先筛选为2个进行计算
     * ！！排序：先排个位数 把所有数字分别放入桶中 如果个位数是5 则放入桶4
     * 把所有元素从桶中取出 放回到数组中 这样数组中所有元素个位数就都排序了
     * 回到！！排序：这步 排十位的数 得到数组十位也是排序的了 依次进行到最高位
     * 得到的数组完成最终排序
     * @param arr
     */
    //对外接口
    static void radixSort(int[] arr){
        //获取一个index [0,index) 为负数 [index，arr.length]是正数
        int zeroIndex = fingPivot(arr, 0, arr.length-1);
        if(arr[zeroIndex]>=0){  //这个index对应是正数
            System.out.println("dayu0");
            //负数组
            //(zeroIndex-1)==-1 说明 这个数组没有负数
            if(zeroIndex-1 != -1)  radixSortAlgorithm(arr,0,zeroIndex-1);
            //正数组
            radixSortAlgorithm(arr,zeroIndex,arr.length-1);
        }else{ //这个index对应是负数
            System.out.println("xiaoyu0");
            //负数  防止下标越界
            radixSortAlgorithm(arr,0,zeroIndex);
            //正数
            radixSortAlgorithm(arr,zeroIndex+1,arr.length-1);
        }
    }
    //此函数的目的是寻找到一个下标 下标左是负数 右是正数
    static int fingPivot(int[] arr, int left, int right){
        int tempLeft = left; //左下标
        int tempRight = right-1; //右下标
        int temp ; //临时变量，作为交换时使用
        //pivot 中轴值需要设立为一个最接近0的值
        int  pivot = 0;
        //寻找绝对值的最小值
        for (int i = 1,min = arr[0] ; i < arr.length; i++) {
            temp = arr[i]>0?arr[i]:(0-arr[i]);
            if(temp<min){
                pivot = i;
                min = temp;
            }
        }
//        if(arr[pivot]<0) pivot++;
        temp = 0;
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
                tempRight -= 1;
            }
            if(arr[tempRight] == pivot) {
                tempLeft += 1;
            }
        }
//        System.out.println("pviot index is "+pivot+'\n'+"Array is ");
//        Sort.show(arr);
        return pivot;
    }
    //基数排序方法 排序全正数或者全负数的
    static void radixSortAlgorithm(int[] arr) {
        //1. 得到数组中最大的数的位数
        int max = arr[0]; //假设第一数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数字的位数
        int maxLength = (max + "").length();
        //因为是10进制 所以一维数组有10个桶 分别储存0 - 9
        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];
        //n是为了去位 这层循环是为了遍历个位到最高位
        //(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //依次去 个位0 十位1 百位2 直到取到最高位，如果没有用0填充
                int numOnDigit = arr[j]/n % 10;
                //放入到对应的桶中
                bucket[numOnDigit][bucketElementCounts[numOnDigit]] = arr[j];
                bucketElementCounts[numOnDigit]++;
            }

            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            //遍历每一桶，并将桶中是数据，放入到原数组 第k个桶
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //遍历当前桶
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
    }
    //区间基数排序 排序全正数或者全负数的
    static void radixSortAlgorithm(int[] arr,int start, int end) {
        if(start == end) return;
        boolean isNegative = false;
        //1. 得到数组中最大的位数
        int extreme = arr[start]; //假设第一数就是最大
        for (int i = start+1; i < end; i++) {
            if (Math.abs(arr[i]) > Math.abs(extreme)) {
                extreme = arr[i];
            }
        }
        //得到最大位数
        int maxLength = (extreme + "").length();
        if(extreme<0) maxLength--;  //负号
        //因为是10进制 所以一维数组有10个桶 分别储存0 - 9
        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        int[][] bucket = new int[10][end-start+1];
        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];
        //n是为了去位 这层循环是为了遍历个位到最高位
        //(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = start; j <= end; j++) {
                //依次去 个位0 十位1 百位2 直到取到最高位，如果没有用0填充
                int numOnDigit = arr[j]/n % 10;
                if(isNegative){
                    numOnDigit += 9;
                }
                //放入到对应的桶中
                bucket[numOnDigit][bucketElementCounts[numOnDigit]] = arr[j];
                bucketElementCounts[numOnDigit]++;
            }

            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = start;
            //遍历每一桶，并将桶中是数据，放入到原数组 第k个桶
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //遍历当前桶
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
        Sort.show(arr,start,end);
    }
}
