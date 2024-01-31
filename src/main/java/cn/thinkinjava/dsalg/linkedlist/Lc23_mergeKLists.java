package cn.thinkinjava.dsalg.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @author qiuxianbao
 * @date 2024/01/29
 */
public class Lc23_mergeKLists {

    public static void main(String[] args) {
        ListNode list1n3 = new ListNode(5);
        ListNode list1n2 = new ListNode(4, list1n3);
        ListNode list1 = new ListNode(1, list1n2);
        ListNode.print(list1);

        System.out.println();

        ListNode list2n3 = new ListNode(4);
        ListNode list2n2 = new ListNode(3, list2n3);
        ListNode list2 = new ListNode(1, list2n2);
        ListNode.print(list2);

        System.out.println();

        ListNode list3n2 = new ListNode(6);
        ListNode list3 = new ListNode(2, list3n2);
        ListNode.print(list3);

        System.out.println();

//        ListNode[] lists = {};
        ListNode[] lists = {list1, list2, list3};
        ListNode.print(mergeKLists(lists));
    }

    /**
     *
     * 约束条件：
     * K的链表分别都是排好序的（有序的）
     *
     * 返回值是ListNode
     *
     * 思路：
     * 需要有一个容器来存放这 K 个 List，每次取出来1个（最小的）进行构建（通过dummy）
     * 这个容器可以借助于 优先级队列（入参比较器）
     *
     * 定义 dummy 用于构建节点后返回；定义指针 curr = dummy，
     * 定义优先级队列 PriorityQueue，for循环链表链表，queue.offer()入队（相当于链表head在队列中）
     *
     * while循环，队列只要不为空，从优先级队列中取值
     * curr.next = queue.poll() 出一个元素
     * 移动指针，curr = curr.next
     *
     * 然后判断 curr.next 与 null 的关系
     * 如果curr.next != null(取出来的这个链表是否还有元素)，则 queue.offer()
     *
     * 最后返回dummy.next
     * @param lists
     * @return
     */
    private static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length <= 0) {
            return null;
        }

        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode listNode : lists) {
            queue.offer(listNode);
        }

        while (!queue.isEmpty()) {
            curr.next =  queue.poll();
            curr = curr.next;

            if (curr.next != null) {
                queue.offer(curr.next);
            }
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
