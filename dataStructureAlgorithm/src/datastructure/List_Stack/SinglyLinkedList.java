package datastructure.List_Stack;

import java.util.Stack;

//单向链表
public class  SinglyLinkedList<T> extends SinglyNode<T> {
    //先初始化一个头结点，头节点不要动，不要存放具体的数值
    private SinglyNode<T> head = new SinglyNode();

    public SinglyLinkedList() { }

    //添加节点到单向链表 尾插
    public boolean addBack(Object obj) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        SinglyNode temp = head;
        SinglyNode target = new SinglyNode(obj);
        temp.isNotIterate = false;
        //失败的利用反射想解决class问题,这里获取的是类的泛型信息 不是对象的
        /*
        ParameterizedType genericSuperclass01 = (ParameterizedType) (this.getClass().getGenericSuperclass());
        Type[] actualTypeArguments01 = genericSuperclass01.getActualTypeArguments();
        ParameterizedType genericSuperclass02 = (ParameterizedType) target.getClass().getGenericSuperclass();
        Type[] actualTypeArguments02 = genericSuperclass02.getActualTypeArguments();
//        for (Type type:actualTypeArguments01) {
//            System.out.println(type);
//        }
        if(head.next!=null&&actualTypeArguments01[0].equals(actualTypeArguments02[0])){
            temp.isNotIterate = true;
            throw new RuntimeException("type error.");
        }
         */
        if(temp.next==null){
            this.clazz = target.clazz;
        }
        if(!(target.clazz.equals(this.clazz))){
            count--;
            temp.isNotIterate = true;
            //throw new RuntimeException("type error.");
            System.err.println("class cast error");
            return false;
        }
        //遍历链表找到最后一个节点
        while (true) {
            //找到最后一个节点，终止循环
            if (temp.next == null) {
                break;
            }
            //如果没有找到，将temp向后移动
            temp = temp.next;
        }
        //while退出时，temp已经指向的最后的节点
        temp.next = target;
        temp.isNotIterate = true;
        return true;
    }

    //按照id顺序插入
    public boolean addByOrder(SinglyNode singlyNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因此我们找到的temp是位于添加位置的前一个结点，否则插入不进去
        SinglyNode temp =  head;;//辅助指针，初始值在head
        temp.isNotIterate = false;
        boolean flag = false;//标识：表示该插入的对象是否已经在链表中存在了，默认为false（没有存在）
        //遍历，从head开始到链表尾
        while (true) {
            //如果已经在链表尾
            if (temp.next == null) {
                break;
            }
            //如果找到位置，就在temp后面插入
            if (temp.next.id > singlyNode.id) {//temp的id不大于，但是temp.next大于，说明插入位置在temp和temp.next之间
                break;//找到位置，退出循环
            }
            if (temp.next.id == singlyNode.id) {
                flag = true;//说明该节点存在
                break;
            }
            //如果以上条件都不满足，将temp后移
            temp = temp.next;
        }
        //此时得到了一个flag值或者一个temp位置
        //首先判断flag
        if (flag) {
            //说明该节点已经存在
            System.out.printf("%dnode exist\n", singlyNode.id);
            temp.isNotIterate = true;
            return false;
        } else {
            //该节点不存在的话，就在temp后面插入新的节点
            singlyNode.next = temp.next;
            temp.next = singlyNode;
            temp.isNotIterate = true;
            return true;
        }
    }

    //修改节点
    public boolean update(int id,Object obj) {
        //首先确定一下链表是否为空
        if (head.next == null) {
            return false;
        }
        //创建辅助结点来遍历链表
        SinglyNode temp = head.next;
        temp.isNotIterate = false;
        //创建flag变量，判断该节点是否找到
        boolean flag = false;
        while (true) {
            //如果已经遍历到尾节点，终止
            if (temp == null) {
                break;
            }
            if (temp.id == id) {
                //找到该节点
                flag = true;
                break;
            } else {
                temp = temp.next;
            }
        }
        //循环结束后通过flag值判断是否修改
        if (flag) {
            temp.isNotIterate = true;
            if(obj.getClass().equals(this.clazz)) {
                temp.t = obj;
                return true;
            }
            else return false;
        } else {
            System.out.printf("%d node not found!\n", id);
        }
        temp.isNotIterate = true;
        return false;
    }

    //删除节点
    //删除第几个有效节点 index从0开始计算
    public boolean deleteByIndex(int index) {
        //如果链表为空，无法删除
        if (head.next == null||index>this.getLength()) {
            return false;
//            throw new RuntimeException("null linkedlist");
        }
        SinglyNode temp =  head; //下标为-1的节点
        temp.isNotIterate = false;
        //flag表示是否找到该节点的前一位，默认为false
        boolean flag = false;
        int count = -1;
        //找到下标为index的节点
        while (count!= index-1) {
            count++;
            temp = temp.next;
        }
        temp.next=temp.next.next;
        temp.isNotIterate = true;
        return false;
    }
    //思路：
    //1.head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2.说明我们在比较时，是temp.next.heroNo 和要删除的节点的heroNo比较
    public boolean delete(int id) {
        //如果链表为空，无法删除
        if (head.next == null) {
            return false;
//            throw new RuntimeException("null linkedlist");
        }
        //构建辅助节点，帮忙遍历链表
        SinglyNode temp =  head;
        temp.isNotIterate = false;
        //flag表示是否找到该节点的前一位，默认为false
        boolean flag = false;
        while (true) {
            //如果遍历到最后一位，说明该节点不存在，终止循环
            if (temp.next == null) {
                break;
            }
            if (temp.next.id == id) {
                //找到了要删除的节点的上一位
                flag = true;
                break;
            } else {
                //没有找到，继续往后走
                temp = temp.next;
            }
        }
        //循环终止后通过判断flag值决定是否删除节点
        if (flag) {
            //如果要删除的节点在最后一位，则将上一位的next指向null
            /*if (temp.next.next==null){
                temp.next = null;
            }
            else {
                //要删除的节点不在最后一位
                temp.next = temp.next.next;
            }*/
            //如果要删除的节点在最后一位，则temp.next.next本身就等于null，以上两种情况可以合并
            temp.next = temp.next.next;
            temp.isNotIterate = true;
            return true;
        } else {
            System.out.printf("%d node not found!\n",id);
        }
        temp.isNotIterate = true;
        return false;
    }


    public int  getLength() {
        //判断链表是否为空
        if (head.next == null) {
            return 0;
        }
        //因为头节点不能动，所以需要一个辅助节点来遍历
        SinglyNode temp = head.next;
        temp.isNotIterate = false;
        int count = 0 ;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //将temp后移
            temp = temp.next;
            count++;
        }
        temp.isNotIterate = true;
        return count;
    }

    public void showList() {
        //判断链表是否为空
        if (head.next == null||head.next.next == null) {
            System.out.println("null list");
            return;
        }
        //因为头节点不能动，所以需要一个辅助节点来遍历
        SinglyNode temp = head.next;
        temp.isNotIterate = false;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //如果还没有遍历到最后
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
        temp.isNotIterate = true;
    }
    //面试题
    //逆序打印
    public void showListInReverseOrder() {
        Stack<SinglyNode> stack = new Stack<>();
        SinglyNode temp = head.next;
        temp.isNotIterate = false;
        while(temp!= null) {
            //入栈
            stack.push(temp);
            temp = temp.next;
        }
        temp.isNotIterate = true;
        while(!(stack.empty()==true)){
            System.out.println(stack.pop());
        }
    }

    //反转单链表
    public void reverse(){
        //判断链表是否为空
        if (head.next == null) {
            return;
        }
        SinglyNode temp = head.next;
        //递归实现
        if(temp.next!=null){
            //this.reverseByRecursion(temp);
            this.reverseByIterate(temp);
        }
    }
    //因为需要回溯 所以递归
    //辅助递归函数，传入当前节点，判断是否是倒数第二个节点，并且反转该节点与下一个节点的顺序关系
    private SinglyNode reverseByRecursion(SinglyNode singlyNode){
        if (singlyNode.next.next==null){
            head.next=singlyNode.next;
            singlyNode.next.next=singlyNode;
            singlyNode.next=null;
        }else{
            reverseByRecursion(singlyNode.next).next=singlyNode;//返回值也是当前节点的下一个节点
            singlyNode.next=null;
        }
        return  singlyNode;
    }
    //辅助函数 遍历
    private void reverseByIterate(SinglyNode firstSinglyNode){
        SinglyNode current = firstSinglyNode;
        //临时变量作为中间值
        SinglyNode last = new SinglyNode();    //用于存储当前节点  并作为下次循环的当前节点的上一个节点
        SinglyNode next = null;                 //用于存储当前节点的下一个节点
        while(current!=null){
            next = current.next;
            current.next = last.next;
            last.next = current;
            current= next;
        }
        head.next=last.next;
    }
    //查找倒数第num个元素
    public Object getDataFromBack(int num){
        if(num>this.getLength()) {
            System.err.println("index greater than length");
            return null;
        }
        //这个元素是正数第length+1-num
        SinglyNode temp = head.next;
        int count = 1;
        temp.isNotIterate = false;
        while(count!=this.getLength()+1-num){
            temp = temp.next;
            count++;
        }
        temp.isNotIterate = true;
        return temp.nodeData;
    }

}

//定义Node，每一个Node对象就是一个节点
class SinglyNode<T> extends NodeData<T> {
    public static int count=1;
    public int id;
    public Object nodeData;
    SinglyNode<T> next;

    public SinglyNode() {
        super();
    }

    public SinglyNode(T t) {
        super(t);
        this.nodeData = this.t;
        this.t = t;
        this.id = count;
        if(isNotIterate) count++;
    }

    //为了显示方法，我们重写toString
    @Override
    public String toString() {
        return "ObjNode{" +
                "id=" + id +
                ", T=" + t.toString() +
                '}';
    }
}
class NodeData<T>{
    public static boolean isNotIterate = true;
    public T t;
    public Class clazz;
    public NodeData(T t) {
        this.t = t;
        this.clazz = t.getClass();
        System.out.println(clazz);
    }
    public NodeData() {
    }
}

