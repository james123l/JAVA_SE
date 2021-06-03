package datastructure.List_Stack;

public class MySinglyLinkedListDemo {
    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.addBack(1);
        singlyLinkedList.addBack(2);
        singlyLinkedList.addBack(3);
        singlyLinkedList.addBack(4);
        singlyLinkedList.showList();
        System.out.println("-------------------------");
        System.out.println(singlyLinkedList.getLength());
        System.out.println(singlyLinkedList.getDataFromBack(2));


    }
}

