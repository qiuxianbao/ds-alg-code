package cn.thinkinjava.dsalg.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例：
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author qiuxianbao
 * @date 2024/01/16
 */
public class Lc1_twoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

    /**
     * O(n)
     *
     * 思路：循环 + map
     *
     * 返回值是int[]
     * 二数之和可以结合 map 变成二数之差
     *
     * 定义一个map（key为数组中的值，value为key在数组中的位置）
     * 循环数组，得到 remain = target - nums[i]
     * 判断 remain 是否在map中
     *      是，返回i以及map.get(remain)
     *      否则，添加到map中
     * 循环结束，则为找不到这样的值
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            if (map.containsKey(remain)) {
                return new int[]{map.get(remain), i};
            }

            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("no solution");
    }
}
