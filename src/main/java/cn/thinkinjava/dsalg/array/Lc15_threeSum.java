package cn.thinkinjava.dsalg.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，
 * 判断 nums 中是否存在三个元素 a，b，c 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *  [-1, 0, 1],
 *  [-1, -1, 2]
 * ]
 *
 * @author qiuxianbao
 * @date 2024/01/16
 */
public class Lc15_threeSum {

    public static void main(String[] args) {
        int[] nums = {-4, 4, -1, 0, 1, 2, -1, -4};
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }


    /**
     * 思路：
     * 三数之和，程序化思维：确定第一个数，再找另外2个数
     * 排序（只有排序，后面的滑动窗口才有意义） + for循环（第1个位置）、while后2个位置（滑动窗口）
     *
     * 返回值是List<List>
     * 三数之和可以变成找三个索引位置
     *
     * 由小到大进行Arrays.sort排序，{-4, -4, -1, -1, 0, 1, 1, 2}
     * 要考虑数字重复跳过问题
     *
     * 因为要找3个数相加等于 0，也就是要找到3个位置
     * for循环nums，可以得到3个位置 i, l=i+1, r=nums.length-1
     * i确定第1个数，l和r确定后2个数(while循环，滑动窗口, 从两边到中间移动)
     *
     * for循环条件：i=0, i < nums.length - 2 && nums[i] <= 0（
     *      因为要留出另外2个数的索引，所以 i < nums.length -2
     *      如果nums[i] 大于0， 往后面的数据就不需要找了，题目是三数之和等于0
     * ） 此处没有在for循环中进行i++，因为当找到值之后，需要对 i 也做去重判断
     *
     * while循环(l < r)
     * 用sum = nums[i] + nums[l] + nums[r]和0的值进行比较
     * 如果小于0，l++
     * 如果大于0，r--
     * 如果等于0，i,l,r 添加到list。 l++, r--继续找（此处要对l和r判断去重）
     *
     * l>r则没找到sum=0的值，i++继续下一轮循环（此处要对i进行去重判断）
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; ) {
            int l = i + 1, r = nums.length - 1;
            // 找的是2个位置，所以没有 =
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[l++], nums[r--]));

                    // l去重
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }

                    // r去重
                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }

                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }

            i++;

            // i去重
            while (i < nums.length - 2 && nums[i] == nums[i - 1]) {
                i++;
            }
        }

        return result;
    }

}
