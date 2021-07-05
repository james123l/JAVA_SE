package datastructure.List_Stack;

import java.util.ArrayList;

public class MyCircleLinkedListDemo {
    //环形链表解决约瑟夫问题
    public static void main(String[] args) {
        CircleLinkedList circleSingleLinkedList = new CircleLinkedList();
        ArrayList solution = circleSingleLinkedList.solution(5, 1, 2);//24153
        //31524
        solution.stream().forEach(System.out::println);
    }

}

