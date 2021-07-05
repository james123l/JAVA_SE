package leetcode.list;

public class N21_MergeTwoSortedLists {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();     // 设定头节点
        ListNode mark = head;
        while(l1!=null && l2!= null){
            if(l1.val>l2.val){
                mark.next = l2;
                l2 = l2.next;
            }else{
                mark.next = l1;
                l1 = l1.next;
            }
            mark = mark.next;
        }
        //判断谁是空
        if(l1 == null){
            mark.next = l2;
        }else{
            mark.next = l1;
        }
        return head.next;
    }
}

