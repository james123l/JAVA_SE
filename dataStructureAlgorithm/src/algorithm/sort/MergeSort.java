package algorithm.sort;

public interface MergeSort {
    /**
     *归并排序:devide and conquer 分治算法
     * 把一个数组分成只有一个数的数组 然后合并 每次合并都要排序
     * 合并后是一个数组分成两半 左边和右边分别从头到尾和另一个数组对比 并且依次加入到临时数组，最终比对结束 把临时数组的值复制回原数组
     * 一共需要合并数组arr.length - 1次
     */

    static void mergeSort(int[] arr){
        //归并排序以空间换时间需要额外的同等大小的数组
        split(arr,0,arr.length-1,new int[arr.length]);
    }


    //分+合方法
    static void split(int[] arr, int left, int right, int[] temp) {
        if(left < right) {
            int mid = (left + right) / 2; //中间索引
            //把数组拆分成只有一个值的数组
            //向左递归进行分解
            split(arr, left, mid, temp);
            //向右递归进行分解
            split(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);

        }
    }

    //合并的方法
    /*
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left; // 初始化i, 左边有序序列的初始索引
        int j = mid + 1; //初始化j, 右边有序序列的初始索引
        int t = 0; // 指向temp数组的当前索引

        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {//继续
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，填充到 temp数组
            //然后 t++, i++
            if(arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else { //反之,将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //(二)
        //把有剩余数据的一边的数据依次全部填充到temp
        while( i <= mid) { //左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while( j <= right) { //右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }


        //(三)
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有,而是当前参与排序的数组区间复制回原数组
        t = 0;
        int tempLeft = left; //参与本次排序的数组区间的头
        while(tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }

}
