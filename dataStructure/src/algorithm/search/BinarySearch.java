package algorithm.search;

import java.util.Arrays;

public interface BinarySearch {
    //二分查找方法 需要传入的表是有序的 每次通过对比中间值 并与我们要查找的值匹配来获得下标

    //递归实现
    static int binarySearchRecursion(int[] arr,int key){
        if((arr.length==0||key < arr[0] || key > arr[arr.length-1])) return -1; //没有找到
        int temp = arr.length/2;
        if (temp > key) {//说明在0- arr.length/2 区间内
            return binarySearchRecursion(Arrays.copyOfRange(arr, 0, arr.length / 2), key);
        } else if (temp < key) {//说明在arr.length/2 - arr.length 区间内
//            if(arr.length> arr.length/2)
            return binarySearchRecursion(Arrays.copyOfRange(arr, arr.length / 2, arr.length), key)+arr.length/2;
        } else {//正好找到
            return arr.length / 2;
        }
    }

    //双指针实现
    /*
    需要注意数组长度和二分法分割后的长度是奇数还是偶数
    奇数： 则最大下标是偶数 不到
    1 2 3 4 5 6 7 8 9
    * * * * * * * * *


     */
    static int binarySearch(int[] arr,int key){
        int high = arr.length-1,low = 0, mid ;
        if((arr.length==0||key < arr[low] || key > arr[high] )) return -1; //没有找到
        while(high>low){
            mid = (high+ low)/2;
            if(mid == key){//找到
                return mid;
            }else if(mid>key){
                high = mid;
            }else if(mid<key){
                low = mid;
            }else{//找到
                return mid;
            }
        }
        return -1;
    }
}
