package cn.thinkinjava.dsalg.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU
 * 双向循环链表实现
 *
 * @author qiuxianbao
 * @date 2024/02/14
 * @since ace_1.4.0_20240109
 */
public class Lc146_LRU {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        System.out.println(cache);  // LRUCache{capacity=2, cacheMap.size=1, cacheMap={1=ListNode{key=1, value=1}}, dummy=ListNode{key=null, value=null}}
        cache.put(2, 2);
        System.out.println(cache);  // LRUCache{capacity=2, cacheMap.size=2, cacheMap={1=ListNode{key=1, value=1}, 2=ListNode{key=2, value=2}}, dummy=ListNode{key=null, value=null}}

        System.out.println(cache.get(1));  // 返回 1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache);  // LRUCache{capacity=2, cacheMap.size=2, cacheMap={1=ListNode{key=1, value=1}, 3=ListNode{key=3, value=3}}, dummy=ListNode{key=null, value=null}}

        System.out.println(cache.get(2)); // 返回 -1 (未找到)

        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache);  // LRUCache{capacity=2, cacheMap.size=2, cacheMap={3=ListNode{key=3, value=3}, 4=ListNode{key=4, value=4}}, dummy=ListNode{key=null, value=null}}


        System.out.println(cache.get(1)); // 返回 -1 (未找到)
        System.out.println(cache.get(3)); // 返回  3
        System.out.println(cache.get(4)); // 返回  4
    }

    /**
     * 思路:
     * 有 key 有 value，那么这个结点的 data有 int key, int value
     * 同时要具备很容易找到 头部（每次添加或获取时需要移动的） 和 尾部（删除） 节点
     * 因此，选择双向循环链表
     *
     * 为了减少查找，可以借助于Map当做缓存（快速获取元素），用于表示实际容器大小
     * 还需要有一个LRU的大小 capacity，用于和实际容器大小 cacheMap.size 比较
     *
     * put操作
     *      key存在，则更新 value，放到头部
     *      否则，判断是否超过容量
     *            超过，先删除链表尾部元素，然后再新增，放到头部
     *            否则，新增，放到头部
     * get操作
     *      每次都更新当结点到链表头部
     *
     * 不管是put，还是get，都保证头部都是最新的节点
     *
     * 定义 capacity，用于初始化存储容量
     * 定义一个 map 用于记录元素，方便判断是否存在以及根据 key去获取节点
     * 定义一个 双向循环链表，方便获取链表头部/尾部元素，便于添加/删除
     *
     * 定义构造函数，用于初始化数据包括dummy节点
     */
    static class LRUCache {
        // LRU大小
        int capacity;
        // 缓存，方便获取元素, size 就是元素实际的大小
        Map<Integer, ListNode> cacheMap;
        // 双向循环链表
        // dummy.next就是 head 节点，dummy.prev就是 tail 节点
        private ListNode dummy;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cacheMap = new HashMap<>();
            // 空元素初始化
            this.dummy = new ListNode();
            this.dummy.prev = this.dummy;
            this.dummy.next = this.dummy;
        }

        /**
         * 放元素
         * 也移动到头部，空间不足时，从尾部删除（需要有一个删除操作）
         *
         * @param k
         * @param val
         */
        public void put(int k, int val) {
            ListNode existNode = cacheMap.get(k);
            if (existNode != null) {
                existNode.value = val;
                move2Head(existNode);
            } else {
                if (cacheMap.size() >= this.capacity) {
                    // 删除尾部结点
                    ListNode tail = dummy.prev;
                    delete(tail);
                    // 删除缓存
                    cacheMap.remove(tail.key);
                }

                // 构建新节点，添加到头部
                ListNode listNode = new ListNode(k, val);
                add2Head(listNode);
                cacheMap.put(k, listNode);
            }
        }


        /**
         * 获取元素
         * 时时移动到头部
         *
         * @param k
         * @return
         */
        public int get(int k) {
            int result = -1;
            ListNode listNode = cacheMap.get(k);
            if (listNode != null) {
                result = listNode.value;
                move2Head(listNode);
            }
            return result;
        }

        /**
         * 移动到头部
         * 先删除，再添加
         *
         * @param e
         */
        private void move2Head(ListNode e) {
            delete(e);
            add2Head(e);
        }

        /**
         * 添加到头部
         *
         * @param e
         */
        private void add2Head(ListNode e) {
            ListNode head = dummy.next;

            e.next = head;
            head.prev = e;

            e.prev = dummy;
            dummy.next = e;
        }

        /**
         * 删除节点
         *
         * @param e
         */
        private void delete(ListNode e) {
            ListNode p = e.prev;
            ListNode n = e.next;

            p.next = n;
            n.prev = p;

            e.prev = null;
            e.next = null;
        }

        @Override
        public String toString() {
            return "LRUCache{" +
                    "capacity=" + capacity +
                    ", cacheMap.size=" + cacheMap.size() +
                    ", cacheMap=" + cacheMap +
                    ", dummy=" + dummy +
                    '}';
        }

        /**
         * 双向循环链表结构
         */
        class ListNode {
            // 数据
            Integer key;
            Integer value;

            // 前驱指针
            ListNode prev;
            // 后继指针
            ListNode next;

            public ListNode() {
            }

            public ListNode(Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return "ListNode{" +
                        "key=" + key +
                        ", value=" + value +
                        '}';
            }
        }

    }

}
