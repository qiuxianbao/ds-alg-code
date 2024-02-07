package cn.thinkinjava.dsalg.linkedlist;

/**
 * 删除有序链表中的重复元素
 *
 * 示例：
 * 输入：1->2->3->3->5
 * 输出：1->2->3->5
 *
 * @author qiuxianbao
 * @date 2024/02/04
 */
public class Lc83_deleteDuplicates {

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n31 = new ListNode(3, n5);
        ListNode n3 = new ListNode(3, n31);
        ListNode n2 = new ListNode(2, n3);
        ListNode head = new ListNode(1, n2);

        ListNode.print(head);

        System.out.println();

        ListNode.print(deleteDuplicates(head));
    }

    /**
     * 约束条件：有序链表
     * 返回值：链表
     *
     * 注意条件判断
     *
     * 思路：
     * 有序链表，那么重复的值就会紧挨着
     *
     * 使用快慢双指针，
     * 如果值相等，fast就往后去找不相等的值
     * 当值不相等时，就把 fast 的值给 slow.next，然后2个指针同时向后移动
     *
     * 循环结束，慢指针和后面的节点断开
     *
     * 定义 slow = head, fast = head.next
     * while循环
     * if (fast != null) slow.next = fast; slow = slow.next
     * fast = fast.next
     *
     * slow.next = null
     *
     * @param head
     * @return
     */
    private static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head.next;
        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }

            fast = fast.next;
        }

        slow.next = null;
        return head;
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

        public static void print(ListNode head) {
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
