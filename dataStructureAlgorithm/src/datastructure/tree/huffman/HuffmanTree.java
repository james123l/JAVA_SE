package datastructure.tree.huffman;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HuffmanTree {


    /*
    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        Node root = createHuffmanTree(arr);

        //测试一把
        preOrder(root); //

    }
     */



    //编写一个前序遍历的方法
    public static void preOrder(Node root) {
        if(root != null) {
            root.preOrderByStack(root);
        }else{
            System.out.println("是空树，不能遍历~~");
        }
    }

    // 创建赫夫曼树的方法
    /**
     *
     * @param arr 需要创建成哈夫曼树的数组
     * @return 创建好后的赫夫曼树的root结点
     */
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //处理的过程是一个循环的过程
        while(nodes.size() > 1) {

            //排序 从小到大
            Collections.sort(nodes);

            System.out.println("nodes =" + nodes);

            //取出根节点权值最小的两颗二叉树
            //(1) 取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            //(2) 取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);

            //(3)构建一颗新的二叉树
            Node parent = new Node(leftNode.getVal()+ rightNode.getVal());
            parent.setLeft(leftNode) ;
            parent.setRight(rightNode) ;

            //(4)从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5)将parent加入到nodes
            nodes.add(parent);
        }

        //返回哈夫曼树的root结点
        return nodes.get(0);

    }
    private static class Node extends datastructure.tree.binaryTree.nodes.Node implements Comparable{
        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val+"";
        }

        private int val;


        public Node(int val) {
            super(null);
            this.val = val;
        }

        @Override
        public int compareTo(Object obj) {
            if( obj instanceof Node){
                Node node = (Node) obj;
                if (this.getVal()>node.getVal()) return 1;
                if (this.getVal()<node.getVal()) return -1;
                    //默认由低到高，所以加符号改变顺序
                else return 0;
            }
            else throw new RuntimeException("cast exception");
        }
    }
}

