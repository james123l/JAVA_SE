package datastructure.List_Stack;

import java.util.ArrayList;

public class CircleLinkedList{
    //第一个节点
    private JosephNode first = null;
    //添加num个节点
    private boolean add(int num){
        if(num<1) return false;
        JosephNode temp = null;
        for(int i = 0 ; i < num ; i++){
            JosephNode node = new JosephNode(i+1);
            if(i==0){
                first = node ;
                first.next = first;
                temp = first; //存储临时变量 为下一个节点准备
            }else{
                temp.next=node; //把上一个节点的next设为当前节点
                node.next=first;
                temp = node;
            }
        }
        return true;
    }
    //约瑟夫问题的解决方案 环形链表
    public ArrayList solution(int total, int start , int distance ){
        if(!this.add(total)||start>total) throw new RuntimeException("number error");
        ArrayList<Integer> list = new ArrayList();
        //获取起始节点前一个结点
        JosephNode temp = first;
        while(temp.next.id!=start) temp = temp.next;
        int count = 1 ;
        while(true){
            if(temp.next==temp){
                list.add(temp.id);
                break;
            } else if(count!=distance) {
                temp = temp.next;
                count++;
            } else{
                list.add(temp.next.id);
                //temp.next是当前要被移除的节点
                //把当前节点的next指向下个节点的下个节点
                temp.next=temp.next.next;
                count = 1;
                //此处不能更新temp=temp.next 因为temp.next已经指向了下一个进行计数的节点
            }
        }

        return list;
    }


}
class JosephNode{
    int id;
    JosephNode next;
    public JosephNode(int id) {
        this.id = id;
    }
}