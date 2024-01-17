package cn.thinkinjava.dsalg.array;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
 * 找出那个只出现了一次的元素。
 *
 * 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例1：
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例2：
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * @author qiuxianbao
 * @date 2024/01/17
 */
public class Lc136_singleNumber {

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(nums));
    }


    /**
     * 约束条件：数组中只有1个元素只出现1次，其他都出现了2次
     *
     * O(n)
     *
     * 思路：一个整数，异或另外一个整数 2 次，等于本身
     *
     * 原理：
     * 1、一个整数 异或 它本身结果等于0
     * 2、任何整数和 0 异或结果是它本身
     * 010001101
     * 010001101
     * 000000000
     * @param nums
     * @return
     */
    private static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
