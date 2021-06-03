package datastructure.List_Stack;

//双向链表
public class  DoublyLinkedList<T> extends DoublyNode<T> {
    //先初始化一个头结点，头节点不要动，不要存放具体的数值
    DoublyNode<T> head = new DoublyNode();
    public DoublyLinkedList() { }

    //添加节点到双向链表 尾插
    public boolean addBack(Object obj) {
        DoublyNode temp = head;
        DoublyNode target = new DoublyNode(obj);
        temp.isNotIterate = false;
        if(temp.next==null){
            this.clazz = target.clazz;
        }
        if(!(target.clazz.equals(this.clazz))){
            temp.isNotIterate = true;
            //throw new RuntimeException("type error.");
            count--;
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
        target.last = temp;
        temp.isNotIterate = true;
        return true;
    }

    //按照id顺序插入
    public boolean addByOrder(DoublyNode doublyNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        //因为单链表，因此我们找到的temp是位于添加位置的前一个结点，否则插入不进去
        DoublyNode temp =  head;;//辅助指针，初始值在head
        temp.isNotIterate = false;
        boolean flag = false;//标识：表示该插入的对象是否已经在链表中存在了，默认为false（没有存在）
        //遍历，从head开始到链表尾
        while (true) {
            //如果已经在链表尾
            if (temp.next == null) {
                break;
            }
            //如果找到位置，就在temp后面插入
            if (temp.next.id > doublyNode.id) {//temp的id不大于，但是temp.next大于，说明插入位置在temp和temp.next之间
                break;//找到位置，退出循环
            }
            if (temp.next.id == doublyNode.id) {
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
            System.out.printf("%dnode exist\n", doublyNode.id);
            temp.isNotIterate = true;
            return false;
        } else {
            //该节点不存在的话，就在temp后面插入新的节点
            doublyNode.next = temp.next;
            temp.next = doublyNode;
            temp.isNotIterate = true;
            return true;
        }
    }

    //修改节点
    public boolean update(int id, Object obj) {
        //首先确定一下链表是否为空
        if (head.next == null) {
            return false;
        }
        //创建辅助结点来遍历链表
        DoublyNode temp = head.next;
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
    public boolean delete(int id) {
        if (head.next == null) {
            return false;
//            throw new RuntimeException("null linkedlist");
        }
        DoublyNode temp =  head.next;
        temp.isNotIterate = false;
        //flag表示是否找到该节点，默认为false
        boolean flag = false;
        while (true) {
            //如果遍历到最后一位，说明该节点不存在，终止循环
            if (temp == null) {
                break;
            }
            if (temp.id == id) {
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
            temp.last.next= temp.next;
            if (temp.next != null) temp.next.last = temp.last;
            temp.isNotIterate = true;
            return true;
        } else {
            System.out.printf("%d node not found!\n", id);
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
        DoublyNode temp = head.next;
        temp.isNotIterate = false;
        int count =1 ;
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
        DoublyNode temp = head.next;
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
}

//定义Node，每一个Node对象就是一个节点
class DoublyNode<T> extends NodeData<T> {
    public static int count=1;
    public int id;
    public Object nodeData;
    DoublyNode next;
    DoublyNode<T> last;


    public DoublyNode() {
        super();
    }

    public DoublyNode(T t) {
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