package algorithm.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public interface BucketSort {
    /**
     * 桶排序是把一个数组分组分别sort 需要根据不同的数据定制 本例 分为负数  0-19 20-39 40以上
     * @param arr
     */

    public static void bucketSort(int[] arr) {
        // 新建一个桶的集合
        ArrayList<LinkedList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // 新建一个桶，并将其添加到桶的集合中去。
            // 由于桶内元素会频繁的插入，所以选择 LinkedList 作为桶的数据结构
            buckets.add(new LinkedList<Integer>());
        }
        // 将输入数据全部放入桶中并完成排序
        for (int data : arr) {
            int index = getBucketIndex(data);
            insertSort(buckets.get(index), data);
        }
        // 将桶中元素全部取出来并放入 arr 中输出
        int index = 0;
        for (LinkedList<Integer> bucket : buckets) {
            for (int data : bucket) {
                arr[index++] = data;
            }
        }
    }

    /**
     * 计算得到输入元素应该放到哪个桶内
     */
    public static int getBucketIndex(int data) {
        // 实际开发中需要根据场景具体设计
        return data<0?0:data<20?1:data<40?2:3;
    }

    /**
     * 我们选择插入排序作为桶内元素排序的方法 每当有一个新元素到来时，我们都调用该方法将其插入到恰当的位置
     */
    static void insertSort(List<Integer> bucket, int data) {
        ListIterator<Integer> it = bucket.listIterator();
        boolean insertFlag = true;
        while (it.hasNext()) {
            if (data <= it.next()) {
                it.previous(); // 把迭代器的位置偏移回上一个位置
                it.add(data); // 把数据插入到迭代器的当前位置
                insertFlag = false;
                break;
            }
        }
        if (insertFlag) {
            bucket.add(data); // 否则把数据插入到链表末端
        }
    }

}
