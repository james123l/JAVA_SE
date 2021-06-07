package datastructure.tree.binaryTree;

public class ThreadedBinaryTreeDemo {
}
class ThreadedBinaryTree {
    //根节点
    private ThreadedNode root;
    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre 总是保留前一个结点
    private ThreadedNode pre = null;

    public void setRoot(ThreadedNode root) {
        this.root = root;
    }

    //重载一把threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList() {
        //定义一个变量，存储当前遍历的结点，从root开始
        ThreadedNode node = root;
        while(node != null) {
            //循环的找到leftType == 1的结点，第一个找到就是8结点
            //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化
            //处理后的有效结点
            while(node.getLeftType() == 0) {
                node = node.getLeft();
            }

            //打印当前这个结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点,就一直输出
            while(node.getRightType() == 1) {
                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRight();

        }
    }

    //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node 就是当前需要线索化的结点
     */
    public void threadedNodes(ThreadedNode node) {

        //空节点不能线索化
        if(node == null) {
            return;
        }

        //(一)先线索化左子树
        threadedNodes(node.getLeft());
        //(二)线索化当前结点[有难度]

        //处理当前结点的前驱结点
        //以8结点来理解
        //8结点的.left = null , 8结点的.leftType = 1
        if(node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }

        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        //(三)在线索化右子树
        threadedNodes(node.getRight());


    }

    //删除结点
    public void deleteNode(int no) {
        //先判断是不是空树
        if(root != null) {
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if(root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.deleteNode(no);
            }
        }else{
            System.out.println("empty tree");
        }
    }
    //前序遍历接口
    public void preOrder() {
        if(this.root != null) {
            this.root.preOrder();
        }else {
            System.out.println("empty tree");
        }
    }

    //中序遍历接口
    public void infixOrder() {
        if(this.root != null) {
            this.root.infixOrder();
        }else {
            System.out.println("empty tree");
        }
    }
    //后序遍历接口
    public void postOrder() {
        if(this.root != null) {
            this.root.postOrder();
        }else {
            System.out.println("empty tree");
        }
    }

    //前序查找接口
    public Node preOrderSearch(int no) {
        if(root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }
    //中序查找接口
    public Node infixOrderSearch(int no) {
        if(root != null) {
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序查找接口
    public Node postOrderSearch(int no) {
        if(root != null) {
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}
class ThreadedNode extends Node{
    //继承二叉树节点 这样就可以获取父类的公共函数
    //如果此时的lefttype = 0，则表示是左子树，lefttype = 1，则表示是前驱节点
    private int leftType;
    //如果此时的rightType = 0，则表示是右子树，rightType = 1，则表示是后继节点
    private int rightType;

    //利用父类的指针 重写get方法
    @Override
    public ThreadedNode getLeft() {
        return super.getLeft() instanceof ThreadedNode? (ThreadedNode) super.getLeft() :null;
    }

    @Override
    public ThreadedNode getRight() {
        return super.getRight() instanceof ThreadedNode? (ThreadedNode) super.getRight() :null;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public ThreadedNode(Node hi) {
        super(1,"hi");
    }
}
