package cn.thinkinjava.dsalg.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 和等于 k 的最长 子数组 长度
 *
 * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度
 * 如果不存在任意一个符合要求的子数组，则返回 0
 *
 * 示例 1:
 * 输入: nums = [1, -1, 5, -2, 3], k = 3
 * 输出: 4
 * 解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
 *
 * 示例 2:
 * 输入: nums = [-2, -1, 2, 1], k = 1
 * 输出: 2
 * 解释: 子数组 [-1, 2] 和等于 1，且长度最长
 *
 * @author qiuxianbao
 * @date 2024/02/14
 * @since ace_1.4.0_20240109
 */
public class Lc325_maxSubArrayLen {

    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        System.out.println(maxSubArrayLen(nums, 3));    // 4
//        int[] nums = {-2, -1, 2, 1};
//        System.out.println(maxSubArrayLen(nums, 1));    // 2
    }

    /**
     * 什么是子数组？
     * 子数组是原数组中连续的元素组成的数组，不是随机选几个元素组成的数组
     *
     * 返回值是 int
     * 思路： 前缀和 + Map
     *
     * 定义：
     * 设 sum[i] 表示 nums[0] + nums[1] + … + nums[i-1] 的和，称为第 i 位的前缀和。
     * 于是，如果存在两个索引 i 和 j，使得 sum[j] - sum[i] == k，说明找到一个子数组 [i, j-1] ，子数组的和为 k
     * 那么，2个前缀和相减，中间的值等于k，那么中间的这部分就是子数组
     *
     * 转化：
     * 定义一个 map，key为 sum[i]，value为 i,
     * 那么sum[j] - k，判断能否在 map 中找到，如果找得到，即存在这样的 k
     *
     * 定义一个 map 存储 sum[i],
     * 定义一个 sum=0累加，len=0为最大长度
     *
     * for循环nums，int j = 0； j < nums.length; j++
     * sum += nums[i]
     * int preSum = sum - k
     *
     * 判断 map.containsKey(preSum)
     * 如果包含那么len就取 Math.max(len, j - map.get(preSum))
     *
     * map.put(sum, j), 注意需要加一个判断条件：key可能会重复，也就是sum[i]和sum[某一个值]相同
     * 因为取len最长，所以保留原始i值，即添加条件是 !map.containsKey(sum)
     *
     * 示例说明：
     * 原数组：[5, -2, -3, 3]
     * 前缀和：[5,  3,  0, 3], 同时存在两个3，因为要求最长序列，所以map中保存的 index=1的sum=3，而不是index=3的sum=3
     * index： 0   1   2  3
     */
    private static int maxSubArrayLen(int[] nums, int k) {
        /**
         * 注意：
         * 需要初始化 map，key = 0, value = -1，为 0 赋值默认值
         *
         *
         * 数组 {1, -1, 5, -2, 3}
         *
         * j	num[j]	sum		preSum
         * -1			0
         * 0	 1		1		-2
         * 1	-1      0       -3
         * 2	 5		5		 2
         * 3	-2		3		 0
         * 4	 3		6		 3
         */
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{
            put(0, -1);
        }};

        int len = 0, sum = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];

            int preSum = sum - k;
            if (map.containsKey(preSum)) {
                len = Math.max(len, j - map.get(preSum));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, j);
            }
        }

        return len;
    }
}
