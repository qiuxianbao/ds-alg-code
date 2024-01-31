package cn.thinkinjava.dsalg.linkedlist;

/**
 * 说明:合并两个有序链表
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author qiuxianbao
 * @date 2024/01/22
 */
public class Lc21_mergeTwoLists {

    public static void main(String[] args) {
        ListNode list1n3 = new ListNode(4);
        ListNode list1n2 = new ListNode(2, list1n3);
        ListNode list1Head = new ListNode(1, list1n2);
        ListNode.print(list1Head);

        System.out.println();

        ListNode list2n3 = new ListNode(4);
        ListNode list2n2 = new ListNode(3, list2n3);
        ListNode l2head = new ListNode(1, list2n2);
        ListNode.print(l2head);

        System.out.println();

        ListNode.print(mergeTwoLists(list1Head, l2head));
    }


    /**
     * 约束条件：2个有序链表
     *
     * 思路：
     *
     * 返回值是ListNode
     * 2个有序链表合并，涉及2个数字比较大小，还有长度不一致的问题
     *
     * 定义dummy，curr = dummy
     * while循环（l1 != null && l2!= null），l1并且l2都没有遍历完
     *
     * 比较 l1.val 和 l2.val，并且移动指针
     * l1.val < l2.val, curr.next = l1.val, l1 = l1.next
     * 否则，curr.next = l2.val, l2 = l2.next
     *
     * 循环结束，说明l1、l2至少有一个循环完了，这个时候要处理后面的链
     * curr.next = l1 == null ? l2 : l1;
     *
     * 最后返回dummy.next即head
     *
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 定义哑结点
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        // 循环比较
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                // 此处不需要新建结点
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            // 向后循环一个指针
            curr = curr.next;
        }

        // 链表长度不一致问题
        curr.next = l1 == null ? l2 : l1;

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
