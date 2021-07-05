package algorithm.search;

import algorithm.sort.BubbleSort;
import algorithm.sort.ShellSort;

public class Search {
    public static void main(String[] args) {//,20, 32,56,88,14,7
        int[] arr = new int[]{1, 3, 4, 5, 6, 7, 9, 11};
        System.out.println(BinarySearch.binarySearch(arr,5));

    }
    public static void show(int[] arr) {
        for (int i: arr) {
            System.out.printf("%d\t",i);
        }
        System.out.println();
    }
}
