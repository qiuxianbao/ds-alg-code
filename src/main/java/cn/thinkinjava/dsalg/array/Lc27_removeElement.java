package cn.thinkinjava.dsalg.array;

import java.util.Arrays;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地移除所有数值等于val的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1：
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 *
 * 示例 2：
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 *
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 *
 * @author qiuxianbao
 * @date 2024/01/17
 */
public class Lc27_removeElement {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
//        System.out.println(removeElement0(nums, 2));
        System.out.println(removeElement(nums, 2));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 示例1
     *
     * 思路： 快慢双指针
     *
     * 定义慢指针指向第一个元素
     * 定义快指针，当快指针指向的值不等于val的时候，快慢都向前移动，然后交换移动元素，最后返回慢指针
     *
     * 定义slow=0
     * for(int fast = 0; fast < num.length; fast++)
     * if (nums[fast] != val) nums[slow++] = nums[fast++]
     *
     * 最后返回slow
     *
     * @param nums
     * @param val
     * @return
     */
    private static int removeElement0(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow++] = temp;
            }
        }

        return slow;
    }

    /**
     * O(1)
     *
     * 示例2
     * 思路：相向双指针
     *
     * 返回值是int
     *
     * 定义2个指针 l = 0，r = length-1
     *
     * while循环 l <= r
     * l 从左向右找等于 val 的, while(l <= r && nums[l] != val) l++
     * r 从右向左找不等于 val 的, while(l <=r && num[r] == val) r++
     * 如果 l、r 跳出循环了，此时 l<= r, 则把r的值给l，l++, r-- 继续 while 循环
     *
     * 最后返回l
     *
     * @param nums
     * @param val
     * @return
     */
    private static int removeElement(int[] nums, int val) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            while (l <= r && nums[l] != val) {
                l++;
            }

            while (l <= r &&  nums[r] == val) {
                r--;
            }

            if (l <= r) {
                int temp = nums[l];
                nums[l++] = nums[r];
                nums[r--] = temp;
            }
        }

        return l;
    }
}
