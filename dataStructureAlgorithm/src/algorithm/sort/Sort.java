package algorithm.sort;

public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[]{-1,1,3,4,5,9,7,65,12,45,36,95,22,2};
        //quicksort可以处理负数
//        QuickSort.quickSortRecursion(arr);
//        show(arr);
        //不能有负数
//        arr[0] = 6;
//        RedixSort.radixSortAlgorithm(arr);
//        show(arr);
        RedixSort.radixSort(arr);
        show(arr);


    }

    public static void show(int[] arr) {
        for (int i: arr) {
            System.out.printf("%d\t",i);
        }
        System.out.println();
        System.out.println("----------------------------");
    }
    public static void show(int[] arr,int start , int end) {
        for (int i= start; i <= end; i++) {
            System.out.printf("%d\t",arr[i]);
        }
        System.out.println();
        System.out.println("----------------------------");
    }
}
