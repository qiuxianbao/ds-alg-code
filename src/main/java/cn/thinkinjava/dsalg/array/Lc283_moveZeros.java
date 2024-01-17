package cn.thinkinjava.dsalg.array;

import java.time.temporal.ValueRange;
import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * @author qiuxianbao
 * @date 2024/01/17
 */
public class Lc283_moveZeros {

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeros(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 思路：
     * 移除元素，但是保持相对位置
     *
     * 使用快慢指针
     * 慢指针指向0，快指针去找不是0的元素，找到后和慢指针替换
     * 慢指针向前走++
     *
     * 最后，判断slow是否小于length，填充后面的值为0
     *
     * @param nums
     */
    private static void moveZeros(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }

        while (slow < nums.length) {
            nums[slow++] = 0;
        }
    }
}
