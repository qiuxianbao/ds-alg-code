package cn.thinkinjava.dsalg.linkedlist;

/**
 * 说明: 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例1：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 * @author qiuxianbao
 * @date 2024/01/22
 */
public class Lc19_removeNthFromEnd {

    public static void main(String[] args) {

        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode head = new ListNode(1, n2);
        ListNode.print(head);

        System.out.println();

        ListNode.print(removeNthFromEnd(head, 2));
    }


    /**
     * 思路：
     * 要返回ListNode, 采用一个哑节点dummy，最终用来返回 dummy.next = head
     *
     * 要删除倒数第 n 个，采用快慢双指针，slow, fast
     * slow 指向 dummy，fast 先走 n 个节点，指向的是第n个结点
     * 然后 while 只要 fast.next != null, slow 和 fast 同时向后走
     * 删除倒数第 n 个节点（就是 slow.next），则 slow.next = slow.next.next
     *
     * @param head
     * @param n
     * @return
     */
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        // 定义哑结点指向head，用于最后返回
        ListNode dummy = new ListNode();
        dummy.next = head;

        // 快指针先走n个节点
        ListNode slow = dummy, fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除第n个节点
        slow.next = slow.next.next;

        return dummy.next;
    }


    static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        static void print(ListNode head) {
            while (head != null) {
                if (head.next == null) {
                    System.out.print(head.val);
                } else {
                    System.out.print(head.val + ", ");
                }

                head = head.next;
            }
        }

    }
}
