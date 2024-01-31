package cn.thinkinjava.dsalg.linkedlist;

/**
 * 两数相加
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author qiuxianbao
 * @date 2024/01/22
 */
public class Lc2_addTwoNumbers {

    public static void main(String[] args) {

        ListNode list1n3 = new ListNode(3);
        ListNode list1n2 = new ListNode(4, list1n3);
        ListNode list1Head = new ListNode(2, list1n2);
        ListNode.print(list1Head);

        ListNode list2n3 = new ListNode(4);
        ListNode list2n2 = new ListNode(6, list2n3);
        ListNode list2Head = new ListNode(5, list2n2);
        ListNode.print(list2Head);

        ListNode result = addTwoNumbers(list1Head, list2Head);
        ListNode.print(result);
    }

    /**
     * n
     *
     * 思路：
     * 遍历链表，从左向右，顺序两两相加（存在链表长度不一样的情况）
     * 2数相加, 进位 incr = sum / 10, 余数  sum % 10
     *
     * 要返回一个新链表来表示和
     * 首先需要构建一个哑节点 dummy，用指针 curr 指向 dummy，操作的时候使用curr指针，最终返回 dummy.next即可
     *
     * while循环2个链表，循环条件l1 != null || l2 != null || incr>0（当 2个链表相等，但是有进位）
     * l1和l2哪个为空，哪个的值用 0 替代
     * 求 sum = l1.val + l2.val + incr
     * incr = sum / 10
     *
     * 构建新结点 node =  sum % 10
     * curr.next = node
     *
     * 循环下一个节点
     * curr = curr.next
     *
     * l1和l2哪个不为空，哪个就向后移动元素，为空的用 null 代替
     *
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 构建哑结点，curr指针指向dummy
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        int incr = 0;
        while (l1 != null || l2 != null || incr > 0) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + incr;
            incr = sum / 10;

            // 新节点
            ListNode node = new ListNode(sum % 10);
            curr.next = node;

            // 迭代，同时解决链表长度不一致问题
            curr = curr.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

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
                // 最后一个元素
                if (head.next == null) {
                    System.out.println(head.val);
                } else {
                    System.out.print(head.val + ", ");
                }

                head = head.next;
            }
        }
    }
}