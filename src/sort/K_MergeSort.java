package sort;

import java.util.PriorityQueue;
import java.util.Queue;

// K-路合并
public class K_MergeSort {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length <= 0) return null;
        int k = lists.length;
        ListNode head = new ListNode(0);
        ListNode p = head;
        Queue<ListNode> queue = new PriorityQueue<>();
        for (ListNode node : lists) {
            if (node != null) queue.offer(node);
        }
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            p = p.next;
            if (node.next != null) queue.offer(node.next);
        }
        return head.next;
    }

    //[[-10,-9,-9,-9,-7,-2,-1,2,4],[-9,-7,-6,-6,-3,0,1,3],[-10,-9,-2,-1,1,3]]
    public static void main(String[] args) {
        ListNode[] list = new ListNode[3];
        list[0] = ListNode.generate(-10,-9,-9,-9,-7,-2,-1,2,4);
        list[1] = ListNode.generate(-9,-7,-6,-6,-3,0,1,3);
        list[2] = ListNode.generate(-10,-9,-2,-1,1,3);
        ListNode head = new K_MergeSort().mergeKLists(list);
        ListNode.print(head);
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode generate(int... nums) {
        ListNode p = new ListNode(0);
        ListNode head = p;
        for (int x : nums) {
            p.next = new ListNode(x);
            p = p.next;
        }
        return head.next;
    }

    public static void print(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }
}