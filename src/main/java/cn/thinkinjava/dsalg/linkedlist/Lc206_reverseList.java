package cn.thinkinjava.dsalg.linkedlist;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * @author qiuxianbao
 * @date 2024/01/29
 */
public class Lc206_reverseList {

    public static void main(String[] args) {
        ListNode n3 = new ListNode(3);
        ListNode n2 = new ListNode(2, n3);
        ListNode head = new ListNode(1, n2);
        ListNode.print(head);

        System.out.println();

        ListNode.print(reverseList(head));
    }

    /**
     * n
     *
     * 反转链表
     *
     * 思路：
     * 1-> 2-> 3
     * 把2指向1，把3指向2... 这是一个循环的过程
     *
     * 链表的循环一定有一个 curr 用来移动，要想改变链的方向
     * 还需要知道前一个节点（和curr用于改变链的方向）
     * 后一个节点（用于循环curr）
     *
     * 定义 3个指针 pre、curr、next
     * pre = null, curr = head, next;
     *
     * 遍历 while(curr != null) 为 next赋值 next = curr.next
     * curr.next = pre 改变链的方向（原来curr.next 指向的 next = curr.next）
     * 往后移动 pre = curr; curr = curr
     *
     * 最后返回头，就是 pre
     *
     * @param head
     * @return
     */
    private static ListNode reverseList(ListNode head) {
        ListNode pre = null, curr = head, next;
        while (curr != null) {
            next = curr.next;

            // 改变链方向
            curr.next = pre;
            // 向后移动
            pre = curr;
            curr = next;
        }

        return pre;
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
