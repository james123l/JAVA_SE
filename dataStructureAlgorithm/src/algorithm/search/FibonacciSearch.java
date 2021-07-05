package algorithm.search;

import algorithm.sort.Sort;

import java.util.ArrayList;
import java.util.Arrays;

public interface FibonacciSearch {
    /**斐波那契查找
     * 基于数列的特性 fibonacci[k] = fibonacci[k-1]+fibonacci[k-2] 得出 fibonacci[k] -1 = （fibonacci[k-1] -1）+（fibonacci[k-2] -1） +1
     * （fibonacci[k-1] -1）代表去掉中轴的左边数列 （fibonacci[k-2] -1）代表去掉中轴的右边数列 1代表中轴mid，分割后的数组依旧可以根据斐波那契数列继续进行黄金分割
     * 使k和数组长度关联 即把数组扩容成fibonacci[k] 的长度 不够的用最大数填充 使得斐波那契数列的数值和数组长度对应
     *  第一轮运算  如果数值在左数组 则k--  右边对应fibonacci[k-2] 则是k-=2
     *  循环条件是low>high
     *  退出条件是 mid 或者high刚好等于key
     */

    /**
     * 获取一个斐波那契数列
     * @param max  斐波那契数列的最大值限制
     */
    /*
    static int[] getFibonacciArrayWithMaxValue(int max) {
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        list.add(1);
        int count = 2;
        while(count<max){
            list.add(list.get(count-1)+list.get(count-2));
            count++;
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i<list.size();i++) {
            arr[i]=list.get(i);
        }
//        Sort.show(arr);
        return arr;
    }
     */
    //本题需要一个返回数组最大值大于arr数组长度的值 所以改为最大值刚好超过max
    static int[] getFibonacciArrayWithMaxValue(int max) {
        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        list.add(1);
        int count = 2;
        while(count<max){
            list.add(list.get(count-1)+list.get(count-2));
            count++;
        }
        list.add(list.get(count-1)+list.get(count-2));
        int[] arr = new int[list.size()];
        for (int i = 0; i<list.size();i++) {
            arr[i]=list.get(i);
        }
//        Sort.show(arr);
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
        int high = arr.length -1;
        int mid = 0; //存放mid值
        //获取斐波那契数列 最大值比arr的最大值大
        int fibonacciArray[] = getFibonacciArrayWithMaxValue(high);
        int fibonacciIndex = fibonacciArray.length-1; //表示斐波那契分割数值的下标
        //使带有数据的数组长度可以用斐波那契数列fibonacci[k]表示
        int[] temp = Arrays.copyOf(arr, fibonacciArray[fibonacciIndex]);
        //实际上需求使用a数组最后的数填充 temp
        //例如temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for(int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        // 寻找key 此时fibonacciArray[fibonacciIndex]代表参与查找的数组区间长度
        while (low <= high) { // 只要这个条件满足，就可以找
            //对当前数组进行黄金分割
            mid = low + fibonacciArray[fibonacciIndex-1] - 1;
            if(key < temp[mid]) { //向左找
                high = mid - 1;
                //前面有fibonacci[febonacciIndex-1]个元素
                fibonacciIndex--;

            } else if ( key > temp[mid]) { //向右找
                low = mid + 1;
                fibonacciIndex -= 2;
            } else { //key = temp[mid] 找到了
                /*
                需要确定，返回的是哪个下标
                需要判断的原因是： 数组经过了扩充，mid可能存在越界问题 即mid大于原数组长度
                所以需要返回一个小的值 这里的考虑情况是 mid>high的情况 如果mid没有越界 可以返回mid 越界后所有的数值都是arr[high]只能返回high
                 */
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
