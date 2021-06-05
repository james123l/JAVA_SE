package leetcode.algorithmProblems;

public class AddTwoNumbers2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3,new ListNode(1))));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode listNode = Solution.addTwoNumbers(l1, l2);
        while(listNode.next!= null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    static class Solution {
        //官方解法
        public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode p = l1, q = l2, curr = dummyHead;
            int carry = 0;
            //把null节点 = 0 一直执行到两个链都结束
            while (p != null || q != null) {
                int x = (p != null) ? p.val : 0;
                int y = (q != null) ? q.val : 0;
                int sum = carry + x + y;
                carry = sum / 10;   //设立flag 为下一个节点
                curr.next = new ListNode(sum % 10); //创立节点
                curr = curr.next;       //null
                if (p != null) p = p.next;
                if (q != null) q = q.next;
            }
            //判断是否需要新的节点
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
            return dummyHead.next;
        }

        //自己的解法
        public static  ListNode MyaddTwoNumbers(ListNode l1, ListNode l2) {
            return getAllNode(l1,l2,false);
        }
        //递归法求每个节点的和
        public static ListNode getAllNode(ListNode l1, ListNode l2,boolean flag){
            if(l1==null&& l2==null)  return flag?new ListNode(1):null;
            int num1 = l1==null?0:l1.val;
            int num2 = l2==null?0:l2.val;
            int num =flag? num1+num2+1:num1+num2;
            ListNode next = getAllNode(l1==null?null:l1.next,l2==null?null:l2.next,num>9);
            //链表头
            ListNode root = new ListNode(num%10,next);
            return root;
        }
    }
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
