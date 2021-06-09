package datastructure.tree.binaryTree;

public class ThreadedNodePostOrder extends ThreadedNode{
    private Node parent;

    public ThreadedNodePostOrder(Object obj, Node parent) {
        super(obj);
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ThreadedNodePostOrder(Object obj) {
        super(obj);
    }
}
