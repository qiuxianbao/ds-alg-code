package cn.thinkinjava.dsalg.array;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * @author qiuxianbao
 * @date 2024/01/17
 */
public class Lc34_searchRange {

    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums, 8)));

        int[] nums1 = new int[]{5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums1, 6)));
    }

    /**
     * 约束条件：数组是排好序的（升序）
     *
     * 思路：升序数组，也就是说相同的值是放在一块儿的
     * 返回值值int[]
     *
     * 有序数组，时间复杂度是O(log n)
     * 一定是借助于二分查找，先找到 mid
     *
     * 然后从 mid 位置开始，lr左右指针向两边扩展，范围是在在low和high之间
     * 返回时需要各自回退一个指针(初始化l=mid，r=mid，首次循环一定成立)
     *
     * 如果找不到mid，直接返回int[]{-1, -1}
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] searchRange(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return new int[] {-1, -1};
        }

        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            if (target == nums[mid]) {
                int l = mid, r = mid;
                while (l >= low && nums[l] == target) {
                    l--;
                }

                while (r <= high && nums[r] == target) {
                    r++;
                }

                return new int[]{++l, --r};

            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return new int[]{-1, -1};
    }
}
