package algorithm.search;

public interface InterpolationSearch {
    public static int interpolationSearch(int[] arr, int left, int right, int key) {
        //left > right || key < arr[0] || key > arr[arr.length - 1] 必须判断 否则会导致越界
        if (left > right || key < arr[0] || key > arr[arr.length - 1]) {
            return -1;
        }
        // 求出mid, 自适应  数学数字
        int mid = left + (right - left) * (key - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (key > midVal) { // 说明应该向右边递归
            return interpolationSearch(arr, mid + 1, right, key);
        } else if (key < midVal) { // 说明向左递归查找
            return interpolationSearch(arr, left, mid - 1, key);
        } else {
            return mid;
        }
    }
}
