package algorithm.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface BinarySearch {
    //二分查找方法 需要传入的表是有序的 每次通过对比中间值 并与我们要查找的值匹配来获得下标

    //递归实现
    static int binarySearchRecursion(int[] arr,int key){
        if((arr.length==0||key < arr[0] || key > arr[arr.length-1])) return -1; //没有找到
        int temp = arr.length/2;
        if (arr[temp] > key) {//说明在[0- arr.length/2) 区间内
            //确定递归退出条件 因为返回值是从0开始计算 所以index不需要+之前去掉的值
            if(((arr.length/2)-1)>=0) return binarySearchRecursion(Arrays.copyOfRange(arr, 0, (arr.length / 2)-1), key);
            return -1;
        } else if (arr[temp]  < key) {//说明在(arr.length/2 - arr.length] 区间内
            //确定递归退出条件 因为返回值是从(arr.length/2)+1开始计算 所以index需要+之前去掉的值
            //此时应该确定0 到 (arr.length/2)的元素个数
            if(arr.length>= ((arr.length/2)+1))  return binarySearchRecursion(Arrays.copyOfRange(arr, (arr.length/2)+1, arr.length), key)+arr.length/2 +1;
            return -1;
        } else {//正好找到
            return temp;
        }
    }
    //包装下面递归方法 从而可以直接输入数组
    static int binarySearch(int[] arr,int key){
        return binarySearchRecursion(arr,0,arr.length-1,key);
    }
    //求区间寻找 递归
    static int binarySearchRecursion(int[] arr, int start, int end, int target) {
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        int midVal = arr[mid];

        if (target > midVal) { // 向 右递归
            return binarySearchRecursion(arr, mid + 1, end, target);
        } else if (target < midVal) { // 向左递归
            return binarySearchRecursion(arr, start, mid - 1, target);
        } else {

            return mid;
        }

    }
    //完成一个课后思考题:
    /*
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     *
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4. 将Arraylist返回
     */
    //包装下面递归方法 从而可以直接输入数组
    static List<Integer> binarySearchAll(int[] arr,int key){
        return binarySearchAllRecursion(arr,0,arr.length-1,key);
    }

    static List<Integer> binarySearchAllRecursion(int[] arr, int start, int end, int key) {

        System.out.println("hello~");
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (start > end) {
            return new ArrayList<Integer>();
        }
        int mid = (start + end) / 2;
        int midVal = arr[mid];

        if (key > midVal) { // 向 右递归
            return binarySearchAllRecursion(arr, mid + 1, end, key);
        } else if (key < midVal) { // 向左递归
            return binarySearchAllRecursion(arr, start, mid - 1, key);
        } else {
//			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回
            List<Integer> resIndexlist = new ArrayList<Integer>();
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while(true) {
                if (temp < 0 || arr[temp] != key) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp -= 1; //temp左移
            }
            resIndexlist.add(mid);  //
            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while(true) {
                if (temp > arr.length - 1 || arr[temp] != key) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp += 1; //temp右移
            }

            return resIndexlist;
        }

    }

    //双指针实现
    /*
    策略 ：
    定义 low high 指针 分别指向区间的两端 mid是中间值 即（high+low）/2
    如果 mid>target 说明在区间[low ,mid) 此时high = mid-1
    如果 mid<target 说明在区间（mid,high] 此时low = mid+1
    如果 mid=target ,target = mid ,return mid

    注意 有可能到最后 low 和mid是同一个数字  接下来也可能 high = low
     */
    /*

    static int binarySearch(int[] arr,int key){
        int high = arr.length-1,low = 0, mid ;
        if((arr.length==0||key < arr[low] || key > arr[high] )) return -1; //没有找到
        //仅当high>=low的时候继续 因为当mid = high = low之后 很可能low>high 之后死循环
        while(high>=low){
            mid = (high+ low)/2;
            if(arr[mid]>key){//如果 mid>target 说明在区间[low ,mid) 此时high = mid-1
                high = mid - 1;
            }else if(arr[mid]<key){//如果 mid<target 说明在区间（mid,high] 此时low = mid+1
                low = mid + 1;
            }else{//如果 mid=target ,target = mid ,return mid;
                return mid;
            }
        }
        //如果执行到这里 说明没有找到
        return -1;
    }
    static int binarySearchAll(int[] arr,int key){
        int high = arr.length-1,low = 0, mid ;
        if((arr.length==0||key < arr[low] || key > arr[high] )) return -1; //没有找到
        //仅当high>=low的时候继续 因为当mid = high = low之后 很可能low>high 之后死循环
        while(high>=low){
            mid = (high+ low)/2;
            if(arr[mid]>key){//如果 mid>target 说明在区间[low ,mid) 此时high = mid-1
                high = mid - 1;
            }else if(arr[mid]<key){//如果 mid<target 说明在区间（mid,high] 此时low = mid+1
                low = mid + 1;
            }else{//如果 mid=target ,target = mid ,return mid;
                List<Integer> resIndexlist = new ArrayList<Integer>();
                //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
                int temp = mid - 1;
                while(true) {
                    if (temp < 0 || arr[temp] != key) {//退出
                        break;
                    }
                    //否则，就temp 放入到 resIndexlist
                    resIndexlist.add(temp);
                    temp -= 1; //temp左移
                }
                resIndexlist.add(mid);  //
                //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
                temp = mid + 1;
                while(true) {
                    if (temp > arr.length - 1 || arr[temp] != key) {//退出
                        break;
                    }
                    //否则，就temp 放入到 resIndexlist
                    resIndexlist.add(temp);
                    temp += 1; //temp右移
                }
                return mid;
            }
        }
        //如果执行到这里 说明没有找到
        return -1;
    }
    */

}
