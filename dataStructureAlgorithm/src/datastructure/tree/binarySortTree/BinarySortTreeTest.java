package datastructure.tree.binarySortTree;

public class BinarySortTreeTest {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12

        //测试一下删除叶子结点


        binarySortTree.deleteNode(12);


        binarySortTree.deleteNode(5);
        binarySortTree.deleteNode(10);
        binarySortTree.deleteNode(2);
        binarySortTree.deleteNode(3);

        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(1);
        binarySortTree.deleteNode(7);


        System.out.println("root=" + binarySortTree.getRoot());


        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }

}
