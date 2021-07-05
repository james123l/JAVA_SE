package datastructure.List_Stack;

public class MyDoublyLinkedListDemo {
    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.addBack(1);
        doublyLinkedList.addBack(2);
        doublyLinkedList.addBack(3);
        DoublyNode doublyNode = new DoublyNode<>(4);
        doublyNode.id = 4;
        doublyLinkedList.addByOrder(doublyNode);
        doublyLinkedList.showList();
        System.out.println("-------------------------");


    }
}

