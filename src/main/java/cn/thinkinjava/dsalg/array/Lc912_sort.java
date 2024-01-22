package cn.thinkinjava.dsalg.array;

import java.util.Arrays;

/**
 * 给定一个长度为 n 的数组，请你编写一个函数，返回该数组按升序排序后的结果
 *
 * 要求：时间复杂度 O(n^2)，空间复杂度 O(n)
 * 进阶：时间复杂度 O(nlogn)，空间复杂度 O(n)
 *
 * 示例：
 * 输入：[5,2,3,1,4]
 * 返回：[1,2,3,4,5]
 *
 * 注：本题数据范围允许绝大部分排序算法，请尝试多种排序算法的实现。
 *
 * @author qiuxianbao
 * @date 2024/01/19
 */
public class Lc912_sort {

    public static void main(String[] args) {
//        int[] nums = {5, 3, 2, 4, 1};
        int[] nums = {2, 1, 4, 9};
        System.out.println(Arrays.toString(nums));

//        bubbleSort(nums);
//        selectSort(nums);
        quickSort(nums, 0 , nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * nlogn
     *
     * 快速排序
     *
     * 思路：递归
     * 以首元素为基准值 pivot，在给定区间[low, high]， 从右向左 j 找比 pivot 小的值，从左向右 i 找比 pivot 大的值，两两交换
     * 直到 i >= j，跳出循环（此时 i 左边的都比 pivot 小）
     * 把 pivot 和 nums[i] 交换
     * 然后分别递归 基准值i 的左部分和右部分
     *
     * 通过一趟排序，得到一个基准值 pivot，左边的都比 pivot 小，右边的都比 pivot 大，然后【递归】左右
     * 注意：如果 pivot = nums[low]，那么必须 j 指针先走，找到一个比 i 小的值才交换，否则会出错
     *
     * 没有返回值
     *
     * 定义 pivot = nums[low], i = low, j = high
     * while循环(i < j) i 和 j 交换
     * 1、j 从右向左，找到比 pivot 小的 while(i < j && num[j] >= pivot) j--
     * 2、i 从左向右，找到比 pivot 大的 while(i < j && nums[i] <= pivot) i++
     * 3、if (i < j) 则交换nums[i]和nums[j]的值
     *
     * 交换基准值，nums[low] = nums[i], nums[i] = pivot
     *
     * 递归左右序列
     * quickSort(nums, low, i -1)
     * quickSort(nums, i + 1, high)
     *
     * @param nums
     * @param low
     * @param high
     */
    private static void quickSort(int[] nums, int low, int high) {
        // 递归结束条件
        if (low > high) {
            return;
        }

        // 初始基准值
        int pivot = nums[low], i = low, j = high;
        while (i < j) {
            // 从右向左，找比pivot小的值
            while (i < j && nums[j] >= pivot) {
                j--;
            }

            // 从左向右，找比pivot大的值
            while (i < j && nums[i] <= pivot) {
                i++;
            }

            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        // 设置基准值
        nums[low] = nums[i];
        nums[i] = pivot;

        // 递归左右
        quickSort(nums, low, i - 1);
        quickSort(nums, i + 1, high);
    }

    /**
     * n^2
     *
     * 选择排序
     * 双层循环，直接操作原数组，没有返回值
     *
     * 思路：
     * 拿当前数，和后面的所有数比较，最终确定下来一个数
     *
     * 从左到右，拿第一个数和后面的数比较，确定的是第一个数
     * 第一层循环确定第一个数，第二层循环后面的数
     *
     * 2 层for循环
     * 第 1 层：i 的值最大到 length-2（倒数第2个元素）, for (int i = 0; i < nums.length - 1; i++)
     * 第 2 层：j 的值最大到 length-1（最后1个元素），for(int j = i + 1; j < nums.length; j++)
     * 比较nums[i]与num[j]
     *
     * @param nums
     */
    private static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i ++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 找出最小值，从小到大排列
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    /**
     * n^2
     *
     * 冒泡排序
     * 双层循环，直接操作原数组，没有返回值
     *
     * 思路：
     *
     * 两两比较，每趟能确定一个数
     * 两层for循环
     * 第一层循环控制趟数，确定第二层循环的最大值
     * 第二层循环用于比较
     *
     * 2层for循环
     * 第 1 层，i 的值为趟数（2个数1趟；3个数2趟），for(int i = 0; i < num.length -1; i++)
     * 第 2 层，j 的值最大到length-2，比较交换，for(int j = 0; j < num.length -1 -i; j++)
     * 比较 num[j]与 [j+1] 的值进行交换
     *
     * @param nums
     */
    private static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 -i; j++) {
                // 找出最大的数，从小到大排列
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

}
