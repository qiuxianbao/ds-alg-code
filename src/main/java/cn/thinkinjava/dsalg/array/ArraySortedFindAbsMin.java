package cn.thinkinjava.dsalg.array;

/**
 * 给定一个有序数组，该数组中的元素有正有负，请找出绝对值最小的数。
 * 示例：[-5, -3, -1, 0, 2, 4]，返回 0
 *
 * @author qiuxianbao
 * @date 2024/01/16
 */
public class ArraySortedFindAbsMin {

    public static void main(String[] args) {
//        int[] array = {-5, -3, -1, 0, 2, 4};
        int[] array = {-5, -3, -2, 1, 2, 4};
        int minAbs = findAbsMin(array);
        System.out.println(minAbs);
    }

    /**
     * 约束条件：有序数组
     *
     * 思路：
     * 绝对值最小：
     * 对于负数，从左到右，绝对值是在变小
     * 对于正数，从左到右，绝对值是在变大
     *
     * 综上，那就是找靠近0的数
     *
     * 如果一个数小于0，那我应该一直从左到右，先找到绝对值最小的负数。
     * 然后，再和第一个正数去比结果
     *
     * @param array
     * @return
     */
    private static int findAbsMin(int[] array) {
        int i = 0;
        while (array[i] < 0) {
            i++;
        }

        // 等于0的含义，在于绝对值相同的2个数，此处返回正数
        if (array[i] + array[i - 1] <= 0) {
            return array[i];
        }

        return array[i - 1];
    }

}
