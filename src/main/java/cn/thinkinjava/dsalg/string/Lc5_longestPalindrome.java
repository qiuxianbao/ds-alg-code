package cn.thinkinjava.dsalg.string;

/**
 * 最长回文子串
 * <p>
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 定义： 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * @author qiuxianbao
 * @date 2024/12/13
 */
public class Lc5_longestPalindrome {

    public static void main(String[] args) {
        // String s = "babad";
        String s = "cbbd";
        System.out.println(longestPalindrome(s));
    }

    /**
     * 思想：
     * 返回值是String
     *
     * 整体思路是从给的字符串中找到 2 个位置 start、end，然后从s.subString(start, end + 1)截取获得
     * 迭代的条件是，每次的返回回文子串的长度，如果比当前大，start和end就向2遍扩展
     *
     * 剩下的问题就是如何得到回文字串？
     * 思路是由中间向两边扩展（中心扩展法）
     *
     * 说明：
     * 回文中心，有 2 种方式
     *
     * 奇数长度回文：回文中心是1个字符，初始化 l = r =i，  得到的回文长度是奇数，1/3/5
     * 偶数长度回文：回文中心是2个字符，初始话 l=i, r=i+1，得到的回文长度是偶数，0/2/4
     *
     * for循环，i取值到 i < length - 1 即可 (最后一个数就不需要循环了)
     * 2种方式，由回文中心向两边扩展（lr双指针）
     *
     * 由中心向两边扩展，返回的是回文字串的长度
     * 计算2种方式的最大值与原有长度比较，如果大，则覆盖start和end
     */
    private static String longestPalindrome(String s) {
        int start = 0, end = 0;
        // 寻找回文中心
        for (int i = 0; i < s.length() - 1; i++) {
            // 回文中心是1个字符
            int oddLen = expandAroundCenter(s, i, i);
            // 回文中心是2个字符
            int evenLen = expandAroundCenter(s, i, i + 1);
            int len = Math.max(oddLen, evenLen);
            // 计算新的长度是否大于已存在的回文长度
            if (len >= end - start + 1) {
                /**
                 * 注意：回文中心是2个字符，回文长度是偶数时，计算start时，需要先把 len - 1，
                 *
                 * 示例：
                 * 索引        01 2345 6
                 * 字符串      ab dccd e
                 *
                 * 当 i = 3 时，回文中心是 cc，找到的回文字符串是 dccd
                 * start = 3 - (4 - 1) / 2 = 2
                 *       ≠ 3 - 4/2 = 1
                 */
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * 中心扩展法
     * 以传入的参数为中心，向两边扩展，返回回文串的长度
     *
     * 数组求长度
     *  -101234
     *    babad
     * aba = (2 - 1) + 1 = 3
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private static int expandAroundCenter(String s, int left, int right) {
        int l = left, r = right;
        for (; l >= 0 && r < s.length(); l--, r++) {
            if (s.charAt(l) != s.charAt(r)) {
                break;
            }
        }
        // 循环跳出时的l和r是不满足条件的（字符不相等 或者 超界），所以在计算时，有效的回文字符长度索引是 l + 1, r -1，
        // 数组通过索引求长度，需要再 +1
        // (r - 1) - (l + 1) + 1 = r - l - 1
        return r - l - 1;
    }


}
