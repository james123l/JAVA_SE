package datastructure.tree.binaryTree;

import datastructure.tree.binaryTree.nodes.ThreadedNode;

/*线索化： 因为二叉树遍历需要递归，消耗大。所以采用链表的思想把所有节点串联成一个线性结构，打印的时候可以直接顺序打印，但是线索化二叉树就无法通过递归进行遍历和删除了
根据一种遍历模式 例如中序遍历 根据打印出的顺序[1,2,3,4,5,6]，使用当前树的空节点进行从头到尾的串联 ，如同链表一样
2是3的前驱节点 2是1的后继节点
**/
public class ThreadedBinaryTree {
    //根节点
    private ThreadedNode root;
    public void setRoot(ThreadedNode root) {
        this.root = root;
    }
    public void buildCompleteBinaryTree(ThreadedNode... nodes){
        if(nodes.length == 0) return;
        for(int i = 0 ; i < nodes.length/2 ; i++){
            if(2*i+1 < nodes.length) nodes[i].setLeft(nodes[2*i+1]);
            if(2*i+2 < nodes.length) nodes[i].setRight(nodes[2*i+2]);
        }
        setRoot(nodes[0]);
    }

    /**
    中序线索化
     */
    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre 总是保留前一个结点
    private ThreadedNode preNodeInfixOrder = null;
    //从根节点开始线索化
    public void threadedRootByInfixOrderRecursion() {
        this.threadedNodesByInfixOrderRecursion(root);
    }
    //遍历前序线索化二叉树的方法
    public void showThreadedListByInfixOrder() {
        //定义一个变量，存储当前遍历的结点，从root开始
        ThreadedNode node = root;
        /*
        * 因为此时已经把二叉树线索化，所以，只要找到list的头，就可以根据right一直遍历下去
        * */
        while(node != null) {   //后继节点不是空的情形可以继续
            //leftType == 1，具有前驱节点的结点，并且已经被线索化后的有效节点
            while(node.getLeftType() == 0) {
                /*第一轮如果找到的是具有左子树的节点 则继续向左找 从最左边输出是中序遍历的顺序
                *如果不是第一轮，这个节点就是当前打印的断点的后继节点
                * */
                node = node.getLeft();
            }
            //打印当前这个结点
            System.out.print(node.toString()+',');
            //如果当前结点的右指针指向的是后继结点,就一直输出，直到遇到右节点不是后继节点的，不能继续通过线索化遍历
            /*node.getRightType() == 0的节点：这个节点具有原有的右节点，并且此时左节点并未遍历 需要先遍历左节点
            * 如果想继续遍历操作，则需要在不能继续通过线索化遍历之后，进行获取right的操作，进入当前节点的右子树。即中序遍历 left root遍历结束后right需要手动操作，因为root的left原本就不是null， 不能设为后继节点，而读取left和root的操作已经通过线索化避免了回溯
            * */
            while(node.getRightType() == 1) {
                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.print(node.toString()+',');
            }
            //因为左节点和父节点遍历完成 接下来寻找右结点
            node = node.getRight();
        }
    }
    //以传入节点作为根的按照遍历顺序线索化二叉树,其兄弟节点和父节点不会进入list
    public void threadedNodesByInfixOrderRecursion(ThreadedNode node) {

        //空节点不能线索化
        if(node == null) {
            return;
        }

        //(一)先线索化左子树
        //这里会一直找到中序遍历的最左边的 即第一个输出的节点的左节点 ，但是因为是null，所以会return 回到第一个输出的节点
        threadedNodesByInfixOrderRecursion(node.getLeft());

        //(二)线索化当前结点： 设置前驱和后继
        /*node.getLeft() == null 说明这个节点在当前子树不会再进入深层遍历 会return到父节点
         *所以 线索化后应该把它的前驱节点指向pre 即预存的它的父节点，即下一个要输出的节点
         * 如果这个节点是中序遍历输出的第一个节点 此时preNode是null，无前驱节点。所以从root开始线索化可以把整个树串联起来
         */
        //处理当前结点的前驱结点
        if(node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(preNodeInfixOrder);
            //修改当前结点的左指针的类型,是前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        /*
        preNode != null 当前节点的前驱不是null避免了最左边的叶子节点，preNode.getRight() == null 并且前驱节点的右子节点为空
        因为中序遍历顺序是 left root right 所以preNodeInfixOrder必然是当前节点的前驱节点，一般是父节点
        所以此处设置right的目的是打印时不回溯到父节点 可以直接从右节点找到顺序继续打印
         */
        if (preNodeInfixOrder != null && preNodeInfixOrder.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            preNodeInfixOrder.setRight(node);
            //修改前驱结点的右指针类型
            preNodeInfixOrder.setRightType(1);
        }

        //每次递归后，保存当前的节点 使得回溯的时候可以用上
        preNodeInfixOrder = node;

        //(三)在线索化右子树
        threadedNodesByInfixOrderRecursion(node.getRight());
    }


    /**
     前序线索化
     */
    private ThreadedNode preNodePreOrder = null;
    //从根节点开始线索化
    public void threadedRootByPreOrderRecursion() {
        this.threadedNodesByPreOrderRecursion(root);
    }

    //遍历前序线索化二叉树的方法
    public void showThreadedListByPreOrder() {
        ThreadedNode temp = root;
        while(temp!=null){
            //前序需要先输出根节点 这里的root就是第一个输出的节点
            //当right是后继的时候可以一直输出
            while(temp.getLeftType()==0){
                System.out.print(temp.toString()+',');
                temp = temp.getLeft();
            }
            System.out.print(temp.toString()+',');
            //找到下一个后继节点
            temp = temp.getRight();
        }
    }
    //以传入节点作为根的按照遍历顺序线索化二叉树,其兄弟节点和父节点不会进入list
    public void threadedNodesByPreOrderRecursion(ThreadedNode node) {
        //空节点不能线索化
        if(node == null) {
            return;
        }
//        System.out.print(node);System.out.print(preNodePreOrder);
        //(一)线索化当前结点
        /*node.getLeft() == null说明不能继续向左遍历，因为此时是前序，所以下一步是把当前节点线索化
        线索化的方式是，如果左节点不是null 则需要设置当前节点左节点属性为1，
        因为前序是left root right 的顺序 此时应当把当前节点的left设为前一个节点pre
         */
        //处理当前结点的前驱结点
        if(node.getLeft() == null) {
            //前一个处理的节点作为前驱节点
            node.setLeft(preNodePreOrder);
            //修改当前结点的左指针的类型
            node.setLeftType(1);
        }
        //处理后继结点
        /*
        preNode != null 当前节点的前驱不是null，preNode.getRight() == null 并且前驱节点的右子节点为空
         */
        if (preNodePreOrder != null && preNodePreOrder.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            preNodePreOrder.setRight(node);
            //修改前驱结点的右指针类型
            preNodePreOrder.setRightType(1);
        }

        //(二)先线索化左子树
        /*
        此处需判断子节点的类型如果不是线索化节点 才可以继续进行线索化，如果已经被线索化，就会产生死递归
         */
        //每次递归后，保存当前的节点 使得回溯的时候可以用上
        preNodePreOrder = node;
        //这里会一直找到前序遍历的最左边的左节点 ，但是因为是null，所以会return
        if(node.getLeftType()==0)  threadedNodesByPreOrderRecursion(node.getLeft());
        //(三)在线索化右子树
        if(node.getRightType()==0) threadedNodesByPreOrderRecursion(node.getRight());


    }


    //后续线索化-----后续线索化后遍历时需要在原来的节点的成员里加一个parent
    //只有传入的都是ThreadedNodePostOrder 才可以使用
    /**
     后序线索化
     */
    private ThreadedNode preNodePostOrder = null;
    //从根节点开始线索化
    public void threadedRootByPostOrder() {
        this.threadedNodesByPostOrder(root);
    }
    //遍历后序线索化二叉树的方法
    public void showThreadedListByPostOrder() {
        //1、找后序遍历方式开始的节点
        ThreadedNodePostOrder node = (ThreadedNodePostOrder) root;
        while ( node != null && node.getLeftType() == 0 ) {
            node = (ThreadedNodePostOrder) node.getLeft();
        }
        while ( node != null ) {
            //右节点是线索
            if (node.getRightType() == 1) {
                System.out.print(node + ",");
                preNodePostOrder = node;
                node = (ThreadedNodePostOrder) node.getRight();
            } else {
                //如果上个处理的节点是当前节点的右节点
                if (node.getRight() == preNodePostOrder) {
                    System.out.print(node + ",");
                    if (node == root) {
                        return;
                    }
                    preNodePostOrder = node;
                    node = (ThreadedNodePostOrder) node.getParent();
                } else {    //如果从左节点的进入则找到有子树的最左节点
                    node = (ThreadedNodePostOrder) node.getRight();
                    while ( node != null && node.getLeftType() == 0 ) {
                        node = (ThreadedNodePostOrder) node.getLeft();
                    }
                }
            }
        }
    /*
        //定义一个变量，存储当前遍历的结点，从root开始
        ThreadedNode temp = root;
        while(temp != null && temp.getLeftType()==0) {
            temp = temp.getLeft();
        }
        ThreadedNode preNode = null;
        while(temp != null) {
            //右节点是线索
            if(temp.getRightType()==1) {
                System.out.print(temp.toString() + ", ");
                preNode = temp;
                temp = temp.getRight();

            } else {
                //如果上个处理的节点是当前节点的右节点
                if(temp.getRight() == preNode) {
                    System.out.print(temp.toString() + ", ");
                    if(temp == root) {
                        return;
                    }

                    preNode = temp;
//                    temp = temp.parent;

                } else {    //如果从左节点的进入则找到有子树的最左节点
                    temp = temp.getRight();
                    while(temp != null && temp.getLeftType()==0) {
                        temp = temp.getLeft();
                    }
                }
            }

        }

     */
    }
    //以传入节点作为根的以后续遍历为顺序线索化二叉树,其兄弟节点和父节点不会进入list
    /*
    线索化顺序正确 但是存在问题
    不能遍历
     */
    @Deprecated
    public void threadedNodesByPostOrder(ThreadedNode node) {
        //如果node==null, 不能线索化
        if (node == null) {
            return;
        }
        //(一)先线索化左子树
        threadedNodesByPostOrder(node.getLeft());
        //(三)再线索化右子树
        threadedNodesByPostOrder(node.getRight());


        //左指针为空,将左指针指向前驱节点
        //8结点的.left = 上一个节点 , 8结点的.leftType = 1
        if (node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(preNodePostOrder);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点,是下一次进行处理，有点不好理解
        if (preNodePostOrder != null && preNodePostOrder.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            preNodePostOrder.setRight(node);
            //修改前驱结点的右指针类型
            preNodePostOrder.setRightType(1);
        }
        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        preNodePostOrder = node;
        System.out.println(node);
    }
}
