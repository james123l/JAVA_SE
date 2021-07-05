package leetcode.array;

public class N35SearchInsertPosition {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6};
        System.out.println(searchInsert(nums, 5));
    }
    //最快最简洁 少想那么多就没事
    public int searchInsertFast(int[] nums, int target) {
        if (nums.length == 0) return 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < target && nums[i + 1] > target) {
                return i + 1;
            }
        }

        return nums[0] > target ? 0 : nums.length;

    }
    public static int searchInsert(int[] arr,int key){
        if(key<arr[0]) return 0;
        if(key>arr[arr.length-1]) return arr.length;
        int high = arr.length-1,low = 0, mid = (high+ low)/2;
        //仅当high>=low的时候继续 因为当mid = high = low之后 很可能low>high 之后死循环
        while(high>=low){
            mid = (high+ low)/2;
            System.out.println(mid);
            if(arr[mid]>key){//如果 mid>target 说明在区间[low ,mid) 此时high = mid-1
                high = mid - 1;
            }else if(arr[mid]<key){//如果 mid<target 说明在区间（mid,high] 此时low = mid+1
                low = mid + 1;
            }else{//如果 mid=target ,target = mid ,return mid;
                return mid;
            }
        }
        //如果执行到这里 说明没有找到
        if(key<arr[mid]){
            do{
                mid--;
            } while(key<arr[mid]);
            return mid+1;
        }else{
            do{
                mid++;
            } while(key>arr[mid]);
            return mid;
        }
    }
}
