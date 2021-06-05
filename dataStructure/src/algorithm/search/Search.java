package algorithm.search;

import algorithm.sort.BubbleSort;
import algorithm.sort.ShellSort;

public class Search {
    public static void main(String[] args) {//,20, 32,56,88,14,7
        int[] arr = new int[]{1, 3, 4, 5, 6, 7, 9, 11};
        ShellSort.shellSort(arr);
        for (int i= 0 ; i < arr.length; i++) {
            System.out.printf("%d\t",FibonacciSearch.fibonacciSearch(arr,arr[i]));
        }
        System.out.println();

    }
    public static void show(int[] arr) {
        for (int i: arr) {
            System.out.printf("%d\t",i);
        }
        System.out.println();
    }
}
