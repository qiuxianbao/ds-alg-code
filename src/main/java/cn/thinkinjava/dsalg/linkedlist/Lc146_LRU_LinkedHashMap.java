package cn.thinkinjava.dsalg.linkedlist;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个 LRU (Least Recently Used， 最近最少使用) 缓存机制。
 * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例：
 * LRUCache cache = new LRUCache(2);// 缓存容量
 *
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 * @author qiuxianbao
 * @date 2024/02/04
 */
public class Lc146_LRU_LinkedHashMap {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache);  // LRUCache{map={1=1, 2=2}}

        System.out.println(cache.get(1));  // 返回 1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache);  // LRUCache{map={1=1, 3=3}}

        System.out.println(cache.get(2)); // 返回 -1 (未找到)

        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache);  // LRUCache{map={3=3, 4=4}}

        System.out.println(cache.get(1)); // 返回 -1 (未找到)
        System.out.println(cache.get(3)); // 返回  3
        System.out.println(cache.get(4)); // 返回  4
    }

    /**
     * 思路：
     * 可以巧妙借助 LinkedHashMap中的 removeEldestEntry方法
     *
     * 注意：
     * 初始化时，指定初始化大小、负载因子、排序模式
     * 重载方法，判断 size() > capacity时移除旧节点
     */
    static class LRUCache {
        private HashMap<Integer, Integer> map;
        public LRUCache(int capacity) {
            // 排序模式
            // true, access-order（访问顺序），移除元素时按照访问的顺序，它控制了是否在 get 操作后需要将新的 get 的节点重新放到链表的尾部
            // 默认值是false, insertion-order（插入顺序），移除元素时按照插入的顺序
            map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true){
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;
                }
            };
        }

        public void put(int key, int value) {
            map.put(key, value);
        }

        public Integer get(int key) {
            return map.getOrDefault(key, -1);
        }

        @Override
        public String toString() {
            return "LRUCache{" +
                    "map=" + map +
                    '}';
        }
    }

}
