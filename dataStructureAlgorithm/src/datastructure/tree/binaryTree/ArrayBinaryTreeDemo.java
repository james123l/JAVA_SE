package datastructure.tree.binaryTree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        //创建一个 ArrBinaryTree
        ArrayBinaryTree arrBinaryTree = new ArrayBinaryTree(arr);
        arrBinaryTree.preOrder(); // 1,2,4,5,3,6,7
    }
}
//编写一个ArrayBinaryTree, 实现顺序存储二叉树遍历
//并不是真的树 而是数组
class ArrayBinaryTree {
    private int[] arr;//存储数据结点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }
    //前序遍历
    /**
     * @param index 数组的下标
     */
    private void preOrder(int index) {
        //如果数组为空，或者 arr.length = 0
        if(arr == null || arr.length == 0) {
            System.err.println("empty tree");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1 );
        }
        //向右递归遍历
        if((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

}
