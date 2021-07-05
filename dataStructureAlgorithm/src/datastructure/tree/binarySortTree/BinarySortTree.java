package datastructure.tree.binarySortTree;

/*
* 二叉排序树 左子节点小于父节点 右子节点大于父节点
* 需要尽量避免 如果相同 可以随意放置左右
* BST适合中序遍历 因为正好可以输出从小到大的顺序
* */

//创建二叉排序树
public class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找结点
    public Node search(int value) {
        if(root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public Node searchParent(int value) {
        if(root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //编写方法:
    //1. 返回的 以node 为根结点的二叉排序树的最小结点的值
    //2. 删除node 为根结点的二叉排序树的最小结点
    /**
     *
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以node 为根结点的二叉排序树的最小结点的值
     */
    public int deleteRightTreeMinValWithValReturn(Node node) {
        Node target = node;
        //循环的查找左子节点，就会找到最小值
        while(target.left != null) {
            target = target.left;
        }
        //这时 target就指向了最小结点
        //删除最小结点
        deleteNode(target.value);
        return target.value;
    }


    //删除结点
    public void deleteNode(int value) {
        if(root == null) {
            return;
        }else {
            //1.需求先去找到要删除的结点  targetNode
            Node targetNode = search(value);
            //如果没有找到要删除的结点
            if(targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个结点
            if(root.left == null && root.right == null) {
                root = null;
                return;
            }

            //去找到targetNode的父结点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if(targetNode.left == null && targetNode.right == null) {
                //判断targetNode 是父结点的左子结点，还是右子结点
                if(parent.left != null && parent.left.value == value) { //是左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {//是右子结点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { //删除有两颗子树的节点
                int minVal = deleteRightTreeMinValWithValReturn(targetNode.right);
                targetNode.value = minVal;
            } else { // 删除只有一颗子树的结点
                //如果要删除的结点有左子结点
                if(targetNode.left != null) {
                    if(parent != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if(parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else { //  targetNode 是 parent 的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { //如果要删除的结点有右子结点
                    if(parent != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if(parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else { //如果 targetNode 是 parent 的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }

            }

        }
    }

    //添加结点的方法
    public void add(Node node) {
        if(root == null) {
            root = node;//如果root为空则直接让root指向node
        } else {
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder() {
        if(root != null) {
            root.infixOrder();
        } else {
            System.out.println("empty tree");
        }
    }
    public int getMaxDepth(){
        if(root == null) return 0 ;
        if(root.left==null&&root.right == null) return 1;
        return root.getMaxDepth();
    }
}

