package datastructure.tree;


import datastructure.tree.binaryTree.*;
import datastructure.tree.binaryTree.nodes.Node;
import datastructure.tree.binaryTree.nodes.ThreadedNode;

import java.util.ArrayList;
/*
* 数组的所有的元素都在霍夫曼树的叶子节点
* */

public class BinaryTreeTest {
    private Node[] rootForest = new ThreadedNode[4];
    public static void main(String[] args) {
        BinaryTreeTest demo = new BinaryTreeTest();
        demo.getBinaryTreeOrder();
        demo.testPreOrder();
        demo.testInfixOrder();
        demo.testPostOrder();

    }
    public void testPreOrder(){
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        ThreadedNode[] nodes = new ThreadedNode[10];
        for (int i = 0; i < 10; i++) {
            nodes[i] = new ThreadedNode(i);
        }
        tree.buildCompleteBinaryTree(nodes[0],nodes[1],nodes[2],nodes[3],nodes[4],nodes[5],nodes[6],nodes[7],nodes[8],nodes[9]);
        System.out.println("前序线索化遍历：");
        tree.threadedRootByPreOrderRecursion();
        tree.showThreadedListByPreOrder();
        System.out.println();

    }
    public void testInfixOrder(){
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        ThreadedNode[] nodes = new ThreadedNode[10];
        for (int i = 0; i < 10; i++) {
            nodes[i] = new ThreadedNode(i);
        }
        tree.buildCompleteBinaryTree(nodes[0],nodes[1],nodes[2],nodes[3],nodes[4],nodes[5],nodes[6],nodes[7],nodes[8],nodes[9]);
        System.out.println("中序线索化遍历：");
        tree.threadedRootByInfixOrderRecursion();
        tree.showThreadedListByInfixOrder();
        System.out.println();


    }
    public void testPostOrder(){
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        ThreadedNodePostOrder[] nodes = new ThreadedNodePostOrder[10];
        for (int i = 0; i < 10; i++) {
            nodes[i] = new ThreadedNodePostOrder(i);
        }
        tree.buildCompleteBinaryTree(nodes[0],nodes[1],nodes[2],nodes[3],nodes[4],nodes[5],nodes[6],nodes[7],nodes[8],nodes[9]);
        System.out.println("后序线索化遍历：");
        tree.threadedRootByPostOrder();
        tree.showThreadedListByPostOrder();
        System.out.println();


    }
    private void getBinaryTreeOrder(){
        ThreadedNode[] nodes = new ThreadedNode[10];
        for (int i = 0; i < 10; i++) {
            nodes[i] = new ThreadedNode(i);
        }
        //构建二叉树
        SimpleBinaryTree tree = new SimpleBinaryTree();
        tree.buildCompleteBinaryTree(nodes[0],nodes[1],nodes[2],nodes[3],nodes[4],nodes[5],nodes[6],nodes[7],nodes[8],nodes[9]);
        System.out.println("前序遍历顺序:");
        tree.preOrderRecursion();
        System.out.println();
        System.out.println("前序遍历顺序:");
        ArrayList list1 = tree.preOrderByStack();
        for (Object node : list1) {
            System.out.print(node.toString()+',');
        }
        System.out.println();
        System.out.println("中序遍历顺序:");
        tree.infixOrderRecursion();
        System.out.println();
        System.out.println("中序遍历顺序:");
        ArrayList list2 =tree.infixOrderByStack();
        for (Object node : list2) {
            System.out.print(node.toString()+',');
        }
        System.out.println();
        System.out.println("后序遍历顺序:");
        tree.postOrderRecursion();
        System.out.println();
        System.out.println("后序遍历顺序:");
        ArrayList list3 =tree.postOrderByStack();
        for (Object node : list3) {
            System.out.print(node.toString()+',');
        }
        System.out.println();

    }
}
