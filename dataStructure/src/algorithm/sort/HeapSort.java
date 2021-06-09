package algorithm.sort;

public interface HeapSort {
    /*
    堆排序要求堆是个完全二叉树
    大顶堆： root大于左右节点 -->用于升序排序
    特点：arr[i]>=arr[2*i+1] && arr[i]>=arr[2*i+2]  即当前节点大于其左右节点
    小顶堆： root小于左右节点 -->用于降序排序
    特点：arr[i]<=arr[2*i+1] && arr[i]<=arr[2*i+2]  即当前节点小于其左右节点
     */

    /**
     * 升序堆排序，使用大顶堆
     * @param arr
     */
    static void heapSortASCOrder(int[] arr){
        int temp;
        //把无序序列构建成大顶堆，从左到右，从下往上依次填入完全二叉树
        //arr.length / 2 - 1 是数组中最后非叶子节点（从上往下从左往右计算）
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            toMaxHeap(arr,i,arr.length);
        }
        /*
        1.将堆顶元素与末尾元素交换，将最大元素沉到数组末尾
        2.调整结构 使其满足堆 然后继续交换堆顶元素与当前末尾元素 ，反复执行调整交换直至数组有序
         */
        for (int i = arr.length - 1; i > 0 ; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            toMaxHeap(arr,0,i);
        }
    }
    //将一个数组虚拟成一个大顶堆
    /**
     * 将以i对应的非叶子节点的树调整成大顶堆
     * @param arr 数组
     * @param i   非叶子节点在数组中的索引
     * @param length 对多少个元素进行调整
     */
    static void toMaxHeap(int[] arr,int i, int length){
        //先记录下当前非叶子节点对应的值
        int temp = arr[i];
        /*
        k = i * 2 + 1  --> k 指向了i节点的左子节点 /   k = k * 2 + 1 更新至下一个左节点
         */
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1 ) {
            //k + 1 < length提高效率防止越界 / arr[k] < arr[k+1] 左子节点小于右子节点
            //使得k指向左或者右子节点的最大值
            if (k + 1 < length && arr[k] < arr[k+1]){
                k++;    //k指向右子节点
            }
            if(arr[k] > temp){  //子节点大于父节点
                //把较大的值赋值给当前节点
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        //循环结束后已经把以i为父节点的树的最大值放置在了树的最顶点
        arr[i] = temp;
    }
}
