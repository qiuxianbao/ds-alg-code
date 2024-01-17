package cn.thinkinjava.dsalg.array;

/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值target，
 * 写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 *
 * 示例2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *
 * 提示：
 * 你可以假设 nums中的所有元素是不重复的。
 * n将在[1, 10000]之间。
 * nums的每个元素都将在[-9999, 9999]之间
 *
 * @author qiuxianbao
 * @date 2024/01/17
 */
public class Lc704_binarySearch {

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 5, 8};
        System.out.println(binarySearch0(nums, 5));
        System.out.println(binarySearch(nums, 5));

        System.out.println(binarySearch0(nums, 8));
        System.out.println(binarySearch(nums, 8));

//        3 = 11 >>> 1 = 1
//        9 = 1001 >>> 1 = 4
//        System.out.println( 3 * 3 >>> 1);    // 4，乘法的优先级高于移位运算符

//        101 = 5 >>> 2
//        System.out.println( 3 + 2 >>> 1);    // 2, 加法的优先级高于移位运算符
    }


    /**
     * 约束条件：整型数组 nums是一个有序数组（升序）
     *
     * 时间复杂度 O（logn）
     *
     * 二分查找
     * 第一种情况：左闭右闭[low, high] 即 low = 0,  high = nums.length - 1
     *
     * 思路：
     * 返回值是int
     *
     * 定义low=0, high=nums.length-1
     *
     * while(low<=high)
     * int mid = low + ((high - low)) >> 2
     *
     * 判断 target 与 nums[mid] 关系
     * target < nums[mid]，high = mid - 1
     * target > nums[mid], low = mid + 1
     * 否则，返回 mid
     *
     * 循环结束，没找到，返回-1
     *
     * 最后，加上边界判断（比最小值还小，比最大值还大，即不存在
     *
     * @param nums
     * @param target
     * @return
     */
    private static int binarySearch0(int[] nums, int target) {
        if (target < nums[0] ||  target > nums[nums.length - 1]) {
            return -1;
        }

        int low = 0, high = nums.length  - 1;
        // 1、注意边界, low = high 有效
        while (low <= high) {
            // 2、防止low + high超出int范围，造成溢出
//            int mid = (low + high) >> 1;
            // 3、移位运算的优先级要低于加减，所以要多加一个括号
            int mid = low + ((high - low) >> 1);
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找
     * 左闭右开[low, high) 即 low = 0,  high = nums.length
     *
     * @param nums
     * @param target
     * @return
     */
    private static int binarySearch(int[] nums, int target) {
        if (target < nums[0] ||  target > nums[nums.length - 1]) {
            return -1;
        }

        int low = 0, high = nums.length - 1;
        // 1、注意边界, low = high无意义的
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                // 2、保持右开，target 在左区间，在[low, mid)
                high = mid;
            }
        }

        return -1;
    }

}
