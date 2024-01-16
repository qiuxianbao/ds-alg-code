package cn.thinkinjava.dsalg.array;

/**
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内，判断该数组中是否有重复的数。
 *
 * @author qiuxianbao
 * @date 2024/01/16
 */
public class ArrayIsDuplicate {

    public static void main(String[] args) {
//        int[] array = {2, 3, 1, 0, 2, 5, 3};
        int[] array = {0, 1};
        System.out.println(isDuplicate(array));
    }

    /**
     * 条件约束：
     * 1.n个元素
     * 2.数组中所有数字都是 0 - n-1 之间
     *
     * 这 2 个条件意味着：
     * 如果数组不重复（0 - n-1的数字必定都在数组中）
     * 重复，则（0 - n-1的数字至少有一个在数组中重复）
     *
     * 思路：
     * 排序，把正确的数放在正确的位置上
     *
     * 循环数组
     *    while 判断 当前元素和索引下表值是否相同
     *      不相同，判断当前元素的值 array[i] 是否和当前元素值作为索引在数组位置上的值 array[array[i]] 是否相等
     *          相等，直接返回 true
     *          否则，就把 array[i] 和 array[array[i]] 的值交换（相当于给 array[i] 找到了正确的位置），继续while循环
     *    while循环的结束条件：
     *          要么是找到相同的元素了，直接返回；
     *          要么是交换后当前元素的位置也正确了，跳出while循环，继续数组for的下一轮循环
     *
     * 数组循环结束（每个元素都找到了正确的位置），即没有找到重复的数，返回false
     *
     * @param array
     * @return
     */
    private static boolean isDuplicate(int[] array) {
        for (int i = 0; i < array.length; i++) {
            while (array[i] != i) {
                if (array[i] == array[array[i]]) {
                    return true;
                }

                // [3, 1, 0, 2]
                // 交换2个数字，思考为什么注释的这个不行
//                int temp = array[i];
//                array[i] = array[array[i]];
//                array[array[i]] = temp;

                int temp = array[array[i]];
                array[array[i]] = array[i];
                array[i] = temp;
            }
        }

        return false;
    }

}
