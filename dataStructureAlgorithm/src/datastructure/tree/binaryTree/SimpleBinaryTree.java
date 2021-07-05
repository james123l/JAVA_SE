package datastructure.tree.binaryTree;


import datastructure.tree.binaryTree.nodes.Node;

import java.util.ArrayList;

//定义BinaryTree 二叉树
/*
* 前序遍历顺序 VLR：  节点的遍历顺序是 root left right
* 先读取root 再读取左子节点 如果子节点依旧有节点则先读取左节点后继续遍历左子节点的左子节点。
* 遍历完左子节点后回溯一次 如果有右节点则读取右节点 没有则继续回溯，
* */
/*
 * 中序遍历顺序 LDR： 节点的遍历顺序是 left root right
 * 再读取叶子左子节点 回溯后读取root 再读取右子节点
 * */
/*
 * 后序遍历顺序 LRD：节点的遍历顺序是 left right root
 * 先读取root 再读取左子节点 如果子节点依旧有节点则继续遍历左子节点的左子节点。
 * */
public class SimpleBinaryTree {
    //根节点
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void buildCompleteBinaryTree(Node... nodes){
        if(nodes.length == 0) return;
        for(int i = 0 ; i < nodes.length/2 ; i++){
            if(2*i+1 < nodes.length) nodes[i].setLeft(nodes[2*i+1]);
            if(2*i+2 < nodes.length) nodes[i].setRight(nodes[2*i+2]);
        }
        setRoot(nodes[0]);
    }
    //前序遍历接口
    public ArrayList preOrderByStack() {
        if(this.root != null) {
            return this.root.preOrderByStack(root);
        }else {
            System.out.println("empty tree");
            return null;
        }
    }

    //中序遍历接口
    public ArrayList infixOrderByStack() {
        if(this.root != null) {
            return this.root.infixOrderByStack(root);
        }else {
            System.out.println("empty tree");
            return null;
        }
    }
    //后序遍历接口
    public ArrayList postOrderByStack() {
        if(this.root != null) {
            return this.root.postOrderByStack(root);
        }else {
            System.out.println("empty tree");
            return null;
        }
    }



    /**
     * 通过递归算法来删除 查找 遍历。
     * 明显的缺点 不适合数据量较大的树 会导致溢出
     * @param no
     */
    //删除结点
    public void deleteNodeRecursion(int no) {
        //先判断是不是空树
        if(root != null) {
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if(root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.deleteNodeRecursion(no);
            }
        }else{
            System.out.println("empty tree");
        }
    }
    //前序遍历接口
    public void preOrderRecursion() {
        if(this.root != null) {
            this.root.preOrderRecursion();
        }else {
            System.out.println("empty tree");
        }
    }

    //中序遍历接口
    public void infixOrderRecursion() {
        if(this.root != null) {
            this.root.infixOrderRecursion();
        }else {
            System.out.println("empty tree");
        }
    }
    //后序遍历接口
    public void postOrderRecursion() {
        if(this.root != null) {
            this.root.postOrderRecursion();
        }else {
            System.out.println("empty tree");
        }
    }

    //前序查找接口
    public Node preOrderSearchRecursion(int no) {
        if(root != null) {
            return root.preOrderSearchRecursion(no);
        } else {
            return null;
        }
    }
    //中序查找接口
    public Node infixOrderSearchRecursion(int no) {
        if(root != null) {
            return root.infixOrderSearchRecursion(no);
        }else {
            return null;
        }
    }
    //后序查找接口
    public Node postOrderSearchRecursion(int no) {
        if(root != null) {
            return this.root.postOrderSearchRecursion(no);
        }else {
            return null;
        }
    }
}

