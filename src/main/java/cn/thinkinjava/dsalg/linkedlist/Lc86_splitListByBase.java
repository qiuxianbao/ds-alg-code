package cn.thinkinjava.dsalg.linkedlist;

/**
 * 基于 基准值 拆分链表
 *
 * 以给基准值 x，将链表分割成 2 部分，所有小于 x 的结点排在大于或等于 x 的结点之前
 * 请返回重新排列后的链表的头指针
 *
 * 注意：
 * 分割以后保持原来的数据顺序不变。
 *
 * 示例：
 * 9->5->2->7->3, 基准值是4
 * 返回 2->3->9->5->7
 *
 * @author qiuxianbao
 * @date 2024/01/30
 */
public class Lc86_splitListByBase {

    public static void main(String[] args) {
        ListNode n5 = new ListNode(3);
        ListNode n4 = new ListNode(7, n5);
        ListNode n3 = new ListNode(2, n4);
        ListNode n2 = new ListNode(5, n3);
        ListNode head = new ListNode(9, n2);

        ListNode.print(head);

        System.out.println();

        ListNode.print(splitListByBase(head, 4));
    }

    /**
     *
     * 约束条件：
     * 保持原有链表值的想对位置
     *
     * 目标：返回一条新的链表
     *
     * 思路：
     *
     * 循环链表，然后与 base 的值比较，拆分成 2 个链表 small（smallHead、smallTail），big（bigHead，bigTail）
     * smallHead 和 bigHead 都是哑结点，smallTail 和 bigTail初始化时分别指向各自的 head，之后随着循环向后移动
     * 最后，把small和big连接在一起，即 smallTail.next = bigHead.next
     *
     * 定义2个链表，bigHead（哑节点），smallHead(哑节点)，bigTail = bigHead, smallTail = smallHead;
     * while循环链表
     * 如果链表 head.val < base, 那么 smallTail.next = new ListNode(head.val); smallTail = smallTail.next; head=head.next
     * 否则, bigTail.next = new ListNode(head.val); bigTail = bigTail.next; head=head.next
     *
     * 因为小于 base 的结点排在大于或等于 base 的结点之前，也就是 small 链表要放在 big 链表的前面
     * 也即 smallTail.next = bigHead.next;
     *
     * 最后返回 smallHead.next
     *
     * @param head
     * @param base
     * @return
     */
    private static ListNode splitListByBase(ListNode head, int base) {
        ListNode smallHead = new ListNode();
        ListNode bigHead = new ListNode();

        ListNode smallTail = smallHead, bigTail = bigHead;
        while (head != null) {
            if (head.val < base) {
                smallTail.next = new ListNode(head.val);
                smallTail = smallTail.next;
            } else {
                bigTail.next = new ListNode(head.val);
                bigTail = bigTail.next;
            }

            head = head.next;
        }

        smallTail.next = bigHead.next;

        return smallHead.next;
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
