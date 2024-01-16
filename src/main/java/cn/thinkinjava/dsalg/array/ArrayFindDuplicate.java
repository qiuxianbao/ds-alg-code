package cn.thinkinjava.dsalg.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。
 *
 * 请找出数组中第一个重复的数字，没有重复的数字，则返回 -1。
 *
 * 示例：
 * [2,3,1,0,2,5,3]，返回 2
 *
 * @author qiuxianbao
 * @date 2024/01/16
 */
public class ArrayFindDuplicate {

    public static void main(String[] args) {
        int[] array = {2, 3, 1, 0, 2, 5, 3};
//        int[] array = {2, 3, 1, 0};
        System.out.println(findDuplicate(array));
    }

    /**
     * 思路：
     * 通过map进行过滤
     *
     * 定义Map：key为数组中的元素，value为元素在数组中的索引
     *
     * 循环数组
     * 判断map中是否存在：
     * 	存在则返回
     * 	不存在，则放入map中
     *
     * 循环结束，说明没有重复的，直接返回-1
     *
     * 最后考虑数组边界
     * @param array
     * @return
     */
    private static int findDuplicate(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                return array[i];
            }

            map.put(array[i], i);
        }

        return -1;
    }

}
