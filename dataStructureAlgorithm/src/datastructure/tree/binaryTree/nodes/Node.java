package datastructure.tree.binaryTree.nodes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//先创建二叉树结点
public class Node {
    private int no;
    private Object obj;
    private Node left;
    private Node right;

    public Node(Object obj) {
        this.obj = obj;
    }

    public Node(int no, Object obj) {
        this.no = no;
        this.obj = obj;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node [no=" + no + ", Object =" + obj + "]";
    }


    /**
     * 使用栈进行前序 中序 后续遍历
     * @param root
     * @return
     */
    //前序遍历
    public static ArrayList preOrderByStack(Node root) {
        Stack<Node> stack = new Stack<Node>();
        ArrayList list = new ArrayList();
        Node p = root;
        while (p != null || !stack.empty()) {
            while (p != null) {
                list.add(p);
                stack.push(p);
                p = p.left;
            }
            if (!stack.empty()) {
                Node temp = stack.pop();
                p = temp.right;
            }
        }
        return list;
    }
    //中序遍历
    public static ArrayList infixOrderByStack(Node root){
        ArrayList list = new ArrayList();
        Stack<Node> stack = new Stack<Node>();
        Node p = root;
        while(p != null || !stack.empty()){
            while(p != null){
                stack.push(p);
                p = p.left;
            }
            if(!stack.empty()){
                Node temp = stack.pop();
                list.add(temp);
                p = temp.right;
            }
        }
        return list;
    }
    //后续遍历
    public static ArrayList postOrderByStack(Node root){
        ArrayList list = new ArrayList();
        Stack<Node> stack = new Stack<Node>();
        if(root == null)
            return list;
        Node cur,pre = null;
        stack.push(root);
        while(!stack.empty()){
            cur = stack.peek();
            if((cur.left == null && cur.right == null) || (pre != null && (cur.left == pre || cur.right == pre))){
                Node temp = stack.pop();
                list.add(temp);
                pre = temp;
            }
            else{
                if(cur.right != null)
                    stack.push(cur.right);
                if(cur.left != null)
                    stack.push(cur.left);
            }
        }
        return list;
    }
    private static void levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<Node>();
        if(root == null)
            return;
        queue.offer(root);
        while(!queue.isEmpty()){
            Node temp  = queue.poll();
            System.out.print(temp + " ");
            if(temp.left != null)
                queue.offer(temp.left);
            if(temp.right != null)
                queue.offer(temp.right);
        }
    }





    /**
     * 通过递归算法来删除 查找 遍历。
     * 明显的缺点 不适合数据量较大的树 会导致溢出
     * @param no
     */
    //前序递归删除结点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void deleteNodeRecursion(int no) {
		/*
		  	1. 因为此二叉树是单向的，所以判断当前结点的子结点是否需要删除，而不能去判断当前这个结点是不是需要删除结点.
			2. 如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.
		 */
        //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        //防止空指针异常 先判断是不是空
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //3.如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //4.我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.deleteNodeRecursion(no);
        }
        //5.则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.deleteNodeRecursion(no);
        }
    }

    //编写前序遍历的方法 VLR
    public void preOrderRecursion() {
        System.out.print(this + ","); //先输出父结点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrderRecursion();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrderRecursion();
        }
    }

    //中序遍历
    public void infixOrderRecursion() {

        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrderRecursion();
        }
        //输出父结点
        System.out.print(this + ",");
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrderRecursion();
        }
    }

    //后序遍历
    public void postOrderRecursion() {
        if (this.left != null) {
            this.left.postOrderRecursion();
        }
        if (this.right != null) {
            this.right.postOrderRecursion();
        }
        System.out.print(this + ",");
    }

    //前序遍历查找
    public Node preOrderSearchRecursion(int no) {
        System.out.println("进入前序遍历");
        //比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        //1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到结点，则返回
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearchRecursion(no);
        }
        if (resNode != null) {//说明我们左子树找到
            return resNode;
        }
        //1.左递归前序查找，找到结点，则返回，否继续判断，
        //2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearchRecursion(no);
        }
        return resNode;
    }

    //中序遍历查找
    public Node infixOrderSearchRecursion(int no) {
        //判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearchRecursion(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
        if (this.no == no) {
            return this;
        }
        //否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearchRecursion(no);
        }
        return resNode;

    }

    //后序遍历查找
    public Node postOrderSearchRecursion(int no) {

        //判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearchRecursion(no);
        }
        if (resNode != null) {//说明在左子树找到
            return resNode;
        }

        //如果左子树没有找到，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderSearchRecursion(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //如果左右子树都没有找到，就比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
