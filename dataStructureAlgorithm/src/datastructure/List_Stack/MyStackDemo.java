package datastructure.List_Stack;


import datastructure.List_Stack.Stack;

public class MyStackDemo {
    public static void main(String[] args) {
        Stack stack = new Stack(20);
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }
        stack.showStack();
        stack.pop();
        stack.showStack();
    }
}

