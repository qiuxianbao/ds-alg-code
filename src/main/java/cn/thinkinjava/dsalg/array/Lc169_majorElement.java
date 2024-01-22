package cn.thinkinjava.dsalg.array;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数指的是在数组中出现次数大于 n/2 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在众数
 *
 * 示例1：
 * 输入: [3, 2, 3]
 * 输出: 3
 *
 * 示例2:
 * 输入: [2, 2, 1, 1, 1, 2, 2]
 * 输出: 2
 *
 * @author qiuxianbao
 * @date 2024/01/19
 */
public class Lc169_majorElement {

    public static void main(String[] args) {
//        int[] nums = {3, 2, 3};
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums));
    }

    /**
     * 约束条件：数组中一定存在大于 n/2 的数
     * 说明：5个数中一定有3个是一样的
     *
     * 思路：
     * 摩尔投票法 (采用投票相抵消的方式)
     *
     * 定义 majority = nums[0] 和 count = 0
     *
     * for循环nums
     * 如果 num[i] = majority, count++
     * 否则，否则 count--（前提，判断 count = 0? , 等于 0，majority = num[i]开始计）
     *
     * 最后判断count的值
     * 如果count=0，则不存在众数
     * 否则，返回 majority
     *
     * @param nums
     * @return
     */
    private static int majorityElement(int[] nums) {
        int major = nums[0], count = 0;
        for (int num : nums) {
            if (num == major) {
                count++;
            } else {
                if (count == 0) {
                    major = num;
                } else {
                    count--;
                }
            }
        }
        return major;
    }
}
