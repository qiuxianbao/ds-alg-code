package cn.thinkinjava.dsalg.array;

/**
 * 最大子序和
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
 *
 * 说明：子数组是数组中的一个连续部分
 *
 * 示例1：
 * 输入：nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6
 *
 * 示例 2：
 * 输入：nums = [5, 4, -1, 7, 8]
 * 输出：23
 *
 * @author qiuxianbao
 * @date 2024/03/15
 */
public class Lc53_maxSubArray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    /**
     * 思路：
     * 返回值是int
     *
     * 前面数的和 + 当前值
     * 如果比当前值大，则相加作为前缀和（说明：加起来的值并不一定比前面数的和大）；
     * 否则当前值作为前缀和，重新开始子数组（之前的子数组和对当前元素并没有贡献）
     *
     * 动态规划：
     * 用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」， f(i)= max{ f(i−1) + nums[i], nums[i]}
     *
     * 定义 max = nums[0], preSum = 0，过程分析如下：
     * -2, 1, -3, 4, -1, 2, 1, -5, 4
     * -2, preSum = -2, max = -2，此时序列是 -2
     * -2, 1，舍弃-2 preSum = 1, max = 1，此时序列是 1
     *     1，-3  preSum= -2, max = 1，此时序列是 1， -3
     *           4, 舍弃-3前面的，preSum=4, max=4，此时序列是 4
     *           4 ，-1, preSum = 3， max=4
     *           4，-1，2，preSum = 5, max = 5
     *           4，-1，2，1，preSum=6，max=6
     *           4，-1，2，1，-5，preSum=1，max=6
     *           4，-1，2，1，-5，4，preSum=5，max=6
     *
     * 动态规划和递归类似，都是把大问题拆分成小问题解决。
     * 区别在于：
     * 动态规划是自底向上的解决方式。通常使用迭代的方式，从最小的问题开始解决，然后逐步构建解决方案
     * 递归是自顶向下的解决方式，不断调用自身来解决问题，直到达到基本情况
     *
     * @param nums
     * @return
     */
    private static int maxSubArray(int[] nums) {
        int max = nums[0], preSum = 0;
        for (int num : nums) {
            preSum = Math.max(preSum + num, num);
            max = Math.max(preSum, max);
        }
        return max;
    }

}
