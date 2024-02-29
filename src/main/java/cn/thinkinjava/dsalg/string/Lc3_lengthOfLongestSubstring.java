package cn.thinkinjava.dsalg.string;

/**
 * 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例3：
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author qiuxianbao
 * @date 2024/02/29
 */
public class Lc3_lengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "abcabcbb";
//        String s = "bbbbb";
//        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    /**
     *
     * 思路：
     * 返回值是int，定义默认值max设置为0
     *
     * 将字符串转转化为字符数组，借助于StringBuilder.indexOf(String str, int fromIndex)判断是否有重复字符
     * 如果有重复的字符，则从重复字符的下一位开始计算。不管有没有，都需要添加到builder中
     * 每循环一次，比较 builder.length() - fromIndex（重复时用于记录下一位） 的值与 max的大小
     *
     * 说明：builder.length() - fromIndex就是本次循环，不重复的字符串的长度
     *
     *
     * 定义 max，fromIndex，builder
     * for循环字符串s
     *
     * int index = builder.indexOf(ch + "", fromIndex) ！= -1
     * index != -1, fromIndex = index + 1;
     * builder.append(ch)
     *
     * int len = builder.length() - fromIndex;
     * 判断 len > max， max = len
     *
     * 最后返回 max
     *
     * @param s
     * @return
     */
    private static int lengthOfLongestSubstring(String s) {
        int max = 0, fromIndex = 0;
        StringBuilder builder = new StringBuilder(s.length());
        for (char ch : s.toCharArray()) {
            // 找到重复位置，切记从开始位置开始
            int index = builder.indexOf(ch + "", fromIndex);
            if (index != -1) {
                fromIndex = index + 1;
            }

            builder.append(ch);

            int len = builder.length() - fromIndex;
            if (len > max) {
                max = len;
            }
        }

        return max;
    }
}
