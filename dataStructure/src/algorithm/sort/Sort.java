package algorithm.sort;

public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,4,5,9,7,65,12,45,36,95,22,2};
        ShellSort.shellSort(arr);
        show(arr);
        BubbleSort.bubbleSort(arr);
        show(arr);

    }

    public static void show(int[] arr) {
        for (int i: arr) {
            System.out.printf("%d\t",i);
        }
        System.out.println();
    }
}
