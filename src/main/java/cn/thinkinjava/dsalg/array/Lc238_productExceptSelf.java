package cn.thinkinjava.dsalg.array;

import java.util.Arrays;

/**
 * 给定长度为 n 的整数数组 nums，其中 n > 1，
 * 返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * @author qiuxianbao
 * @date 2024/01/17
 */
public class Lc238_productExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    /**
     * 思路：
     * 【除自身以外数组的乘积】 可以分解为 【当前数字前面所有数字的乘积】 X 【当前数字后面所有数字的乘积】
     *
     * 示例：
     * nums = [1, 2, 3, 4]
     *
     * 返回值是数组
     * 定义一个数组 output:int[]
     *
     * 二次循环：
     * 1、从左到右循环，当前位置的值 等于 前面数字的乘积（顺着计算）
     * 第一个索引位置 output 及 product初始值是 1
     * product = [1, 1*1=1, 1*2=2, 2*3=6] →
     * output = [1, 1, 2, 6]
     *
     * 2、从右到左循环，前面的数等于后面数的乘积，初始值product=1（倒着计算）
     * 最后一个索引位置乘积 product 初始值是 1
     * product = [12*2=24, 4*3=12, 1*4=4, 1] ←
     * output = output * product = [1*24=24, 1*12=12, 2*4=8, 6*1=6]
     *
     * 最后返回output
     *
     * @param nums
     * @return
     */
    private static int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        for (int i = 0, product = 1; i < nums.length; i++) {
            output[i] = product;
            product *= nums[i];
        }

        for (int j = nums.length - 1, product = 1; j >= 0; j--) {
            output[j] *= product;
            product *= nums[j];
        }

        return output;
    }

}
