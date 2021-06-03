package datastructure.List_Stack;

public class Stack{
    //单链表实现栈
    private SinglyLinkedList stack = new SinglyLinkedList();
    private int top=-1; //栈顶的索引 从0开始
    private int capacity;
    //构造函数需要传入栈的最大值
    public Stack(int capacity) {
        this.capacity = capacity;
    }
    public boolean isNull(){
        return top == -1;
    }
    //入栈
    public boolean push(Object obj){
        if(top==capacity-1) {
            System.err.println("stack is full");
            return false;
        }
        stack.addBack(obj);
        top++;
        return true;
    }
    //出栈
    public Object pop(){
        Object data =peekTop();
        stack.deleteByIndex(stack.getLength()-1);
        top--;
        return data;
    }
    public Object peekTop(){
        if(top == -1){
            System.err.println("null stack");
            return null;
        }
        //只能出一个栈顶
        Object data = this.stack.getDataFromBack(1);
        return data;
    }
    public void showStack(){
        stack.showList();
    }
}