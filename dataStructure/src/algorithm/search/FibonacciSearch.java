package algorithm.search;

import algorithm.sort.Sort;

import java.util.ArrayList;
import java.util.Arrays;

public interface FibonacciSearch {

    /**
     * 获取一个斐波那契数列
     * @param max  斐波那契数列的最大值限制
     */
    /*
    static int[] getFibonacciArrayWithMaxValue(int max) {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(1);
        int count = 2;
        while(count<max){
            list.add(Integer.parseInt(list.get(count-1).toString())+Integer.parseInt(list.get(count-2).toString()));
            count++;
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i<list.size();i++) {
            arr[i]=Integer.parseInt(list.get(count-1).toString());
        }
        return arr;
    }
     */
    //本题需要一个返回数组最大值大于arr数组长度的值 所以改为最大值刚好超过max
    static int[] getFibonacciArrayWithMaxValue(int max) {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(1);
        int count = 2;
        while(count<max){
            list.add(Integer.parseInt(list.get(count-1).toString())+Integer.parseInt(list.get(count-2).toString()));
            count++;
        }
        list.add(Integer.parseInt(list.get(count-1).toString())+Integer.parseInt(list.get(count-2).toString()));
        int[] arr = new int[list.size()];
        for (int i = 0; i<list.size();i++) {
            arr[i]=Integer.parseInt(list.get(count-1).toString());
        }
        return arr;
    }
    /**
     * 获取一个斐波那契数列
     * @param capacity  斐波那契数列的最大容量限制
     */
    static int[] getFibonacciArrayWithMaxCapacity(int capacity) {
        int[] arr = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            arr[i] = getFibonacciNumberRecursion(i);
        }
        return arr;
    }
    static int getFibonacciNumberRecursion(int index){
        if(index == 1|| index == 0) return 1;
        else return getFibonacciNumberRecursion(index-1)+getFibonacciNumberRecursion(index-2);
    }
    /**
     *
     * @param arr  数组
     * @param key 我们需要查找的关键码(值)
     * @return 返回对应的下标，如果没有-1
     */
    static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length ;
        int mid = 0; //存放mid值
        int fibonacciArray[] = getFibonacciArrayWithMaxValue(high);
        int k = fibonacciArray.length-1; //表示斐波那契分割数值的下标
        int[] temp = Arrays.copyOf(arr, fibonacciArray[k]);
        //实际上需求使用a数组最后的数填充 temp
        //举例:
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for(int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        Sort.show(temp);

        // 使用while来循环处理，找到我们的数 key
        while (low <= high) { // 只要这个条件满足，就可以找
            mid = low + fibonacciArray[k - 1] - 1;
            if(key < fibonacciArray[mid]) { //我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                //为甚是 k--
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            } else if ( key > fibonacciArray[mid]) { // 我们应该继续向数组的后面查找(右边)
                low = mid + 1;
                //为什么是k -=2
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid = f[k - 1 - 2] - 1
                k -= 2;
            } else { //找到
                //需要确定，返回的是哪个下标
                if(mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
