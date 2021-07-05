package datastructure.hashTable;

import java.util.Scanner;

public class HashTableDemo {

    public static void main(String[] args) {

        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    ContainerNode containerNode = new ContainerNode(id, name);
                    hashTab.add(containerNode);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    Object element= hashTab.findElementById(id);
                    System.out.println(element.toString());
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }

}

//创建HashTab 管理多条链表
class HashTab {
    private HashList[] hashListArray;
    private int size;

    //构造器
    public HashTab(int size) {
        this.size = size;
        hashListArray = new HashList[size];
        //？留一个坑, 这时不要分别初始化每个链表
        for(int i = 0; i < size; i++) {
            hashListArray[i] = new HashList();
        }
    }

    //添加
    public void add(ContainerNode containerNode) {
        //根据id ,得到应当添加到哪条链表
        int index = hashFun(containerNode.id);
        //将emp 添加到对应的链表中
        hashListArray[index].add(containerNode);

    }
    //遍历所有的链表,遍历hashtab
    public void list() {
        for(int i = 0; i < size; i++) {
            hashListArray[i].showList();
        }
    }

    //根据输入的id,查找元素
    public Object findElementById(int id) {
        //使用散列函数确定到哪条链表查找
        int index = hashFun(id);
        ContainerNode containerNode = hashListArray[index].findElementById(id);
        if(containerNode!= null) return containerNode.obj;
        else System.err.println("not found");
        return null;
    }

    //编写散列函数, 使用一个简单取模法
    public int hashFun(int id) {
        return id % size;
    }


}

//表示一个节点储存Obj
class ContainerNode {
    public int id;
    public Object obj;
    public ContainerNode next; //next 默认为 null
    public ContainerNode(int id, Object obj) {
        super();
        this.id = id;
        this.obj = obj;
    }
}


class HashList {
    //头指针
    private ContainerNode head; //默认null

    public void add(ContainerNode containerNode) {
        //如果是添加第一个雇员
        if(head == null) {
            head = containerNode;
            return;
        }
        //如果不是第一个节点 则需要定位到最后一个
        ContainerNode temp = head;
        while(true) {
            if(temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = containerNode;
    }

    public void showList() {
        if(head == null) { //说明链表为空
            return;
        }
        ContainerNode temp = head; //辅助指针
        while(true) {
            System.out.printf(" => id=%d name=%s\t", temp.id, temp.obj);
            if(temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }

    //根据id查找
    public ContainerNode findElementById(int id) {
        //判断链表是否为空
        if(head == null) {
            System.err.println("null list");
            return null;
        }
        ContainerNode temp = head;
        while(true) {
            if(temp.id == id) {//找到
                break;
            }
            //退出
            if(temp.next == null) {//当前列表不存在该元素
                temp = null;
                break;
            }
            temp = temp.next;
        }

        return temp;
    }

}
