package cn.thinkinjava.dsalg.linkedlist;

/**
 * 给定一个单链表，把所有的 奇数节点 和 偶数节点 分别排在一起
 *
 * 请注意：
 * 这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(n)，n为节点总数
 *
 * 示例1：
 * 输入：1->2->3->4->5->null
 * 输出：1->3->5->2->4->null
 *
 * 示例2：
 * 输入：2->1->3->5->6->4->7->null
 * 输出：2->3->6->7->1->5->4->null
 *
 * @author qiuxianbao
 * @date 2024/01/30
 */
public class Lc328_splitListByOddEven {

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode head = new ListNode(1, n2);

        ListNode.print(head);
        System.out.println();

        ListNode.print(splitListByOddEven(head));
    }

    /**
     * 要求：
     * O(1)，O(n)
     *
     * 思路：
     * 用 2 个链表指向 odd和 even，然后同时循环，最后把 even 放在 odd 的 后面
     *
     * 定义 2 对指针，odd 和 even
     * oddHead = head, oddTail=head;
     * evenHead=head.next, evenTail=head.next（tail用于指针的循环）
     *
     * while循环（每循环一次跳过2个结点），条件是 evenTail != null && evenTail.next != null
     * oddTail.next = evenTail.next, oddTail = oddTail.next
     * evenTail.next = oddTail.next, evenTail = evenTail.next
     *
     * 最后把 偶数 放在 奇数的 放后面，oddTail.next = evenHead
     *
     * @param head
     * @return
     */
    private static ListNode splitListByOddEven(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode oddHead = head, oddTail = head;
        ListNode evenHead = head.next, evenTail = head.next;
        // 注意这个判断条件
        while (evenTail != null && evenTail.next != null) {
            oddTail.next = evenTail.next;
            oddTail = oddTail.next;

            evenTail.next = oddTail.next;
            evenTail = evenTail.next;
        }

        oddTail.next = evenHead;

        return oddHead;
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
