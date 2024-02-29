package cn.thinkinjava.dsalg.string;

/**
 *
 * 反转字符串中的单词
 *
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 *
 * 示例 2：
 * 输入：s = " hello world "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 *
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 *
 * 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用O(1) 额外空间复杂度的 原地 解法
 *
 * @author qiuxianbao
 * @date 2024/02/29
 * @since ace_1.4.0_20240109
 */
public class Lc151_reverseWords {

    public static void main(String[] args) {
        String s = " a b  cd ";

//        String[] split = s.trim().split(" ");
//        String[] split = s.trim().split(" +");
//        System.out.println(Arrays.asList(split));

        System.out.println(reverseWords(s));
    }


    /**
     *
     * 思路：
     * 将字符串按照空格拆分，然后反转，最后组合
     * 说明：由于空格存在一个或者多个，所以将字符串拆分为数组时，要通过正则来匹配空格
     *
     * 除去字符串首尾空格：String.trim()
     * 按照空格拆分：String.split(String regex)
     * 遍历数组，反转
     * 组合字符串：String.join(CharSequence delimiter, CharSequence... elements)
     *
     * @param s
     * @return
     */
    private static String reverseWords(String s) {
        // 按照正则先拆分
        String[] split = s.trim().split(" +");
        // 原地反转
        reverseArray(split);
        // 通过空格组合
        return String.join(" ", split);
    }

    /**
     * 原地交换
     * 定义2个指针i和j，进行交互
     *
     * @param split
     */
    private static void reverseArray(String[] split) {
        for (int i = 0, j = split.length - 1; i < j; i++, j--) {
            String temp = split[i];
            split[i] = split[j];
            split[j] = temp;
        }
    }
}
