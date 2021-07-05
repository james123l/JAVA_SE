package datastructure.tree.binaryTree.nodes;

public class ThreadedNode extends Node {
    /*中序线索化
     * leftType = 0，则表示是左子树      rightType = 0，则表示是右子树
     * rightType = 1，则表示是前驱节点   rightType = 1，则表示是后继节点
     */
    //继承二叉树节点 这样就可以获取父类的公共函数
    private int leftType;
    private int rightType;

    //利用父类的指针 重写get方法
    @Override
    public ThreadedNode getLeft() {
        return super.getLeft() instanceof ThreadedNode ? (ThreadedNode) super.getLeft() : null;
    }

    @Override
    public String toString() {
        return this.getObj().toString();
    }

    @Override
    public ThreadedNode getRight() {
        return super.getRight() instanceof ThreadedNode ? (ThreadedNode) super.getRight() : null;
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

    public ThreadedNode(Object obj) {
        super(1, obj);
    }
}
