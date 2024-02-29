package cn.thinkinjava.dsalg.string;

/**
 * 字符串相加求和
 *
 * 说明:给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 提示：
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 * 342 + 465 = 807
 *
 * @author qiuxianbao
 * @date 2024/02/29
 * @since ace_1.4.0_20240109
 */
public class Lc415_addStrings {

    public static void main(String[] args) {

//        int i = '0';
//        System.out.println(i);  // 48

//        System.out.println('0'); // 0

//        int i2 = '4';
//        System.out.println(i2); // 52

//        int i3 = '4' - '0';
//        System.out.println(i3); // 4

        String num1 = "342";
        String num2 = "465";
//        System.out.println(new BigDecimal(num1).add(new BigDecimal(num2)));

        System.out.println(addStrings(num1, num2));
    }

    /**
     * 思路：
     *
     * 返回值是字符串，
     *
     * #考虑一
     * 2个字符串逆着相加，加完之后，反转
     * 可以用StringBuilder.reverse()来存储及返回结果
     *
     * #考虑二
     * 相加，进位 incr = sum / 10, 余数  sum % 10
     * 注意：字符串计算，'0'=48,'1'=49..., 所以任意一个字符'ch'在计算数值时要减去'0'，即'ch'-'0'
     *
     * 首先将字符串转换成字符数组
     * for倒叙循环2个chs，条件时i>=0 || j>=0 || incr>0, i--,j--
     * 计算 int sum=(i >= 0 ? chs1[i] - '0' : 0) + (j >= 0 ? chs2[j] - '0' : 0) + incr
     * 计算 incr = sum / 10, 将余数  sum % 10存储到builder中
     *
     * 最后builder.reverse
     *
     * @param num1
     * @param num2
     * @return
     */
    private static String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        char[] chs1 = num1.toCharArray();
        char[] chs2 = num2.toCharArray();

        int incr = 0;
        for (int i = chs1.length - 1, j = chs2.length - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = (i >= 0 ? chs1[i] - '0' : 0) + (j >= 0 ? chs2[j] - '0' : 0) + incr;
            incr = sum / 10;

            builder.append(sum % 10);
        }

        return builder.reverse().toString();
    }

}
