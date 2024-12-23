package cn.thinkinjava.dsalg.string;

/**
 * 钢条切割
 * 当我们给定一段长度为 n 英寸的钢条和对应的一个价格表 p (i), i = 1,2,3,…n
 *
 * 长度i	1	2	3	4	5	6	7	8	9	10
 * 价格pi	1	5	8	9	10	17	17	20	24	30
 *
 * 求切割钢条的方案，使得销售收益 r(n) 最大。（在这里，我们要求切割的钢条必须为整英寸长度）
 * <a href="https://blog.csdn.net/u013309870/article/details/75193592">算法-动态规划</a>
 *
 * @author qiuxianbao
 * @date 2024/12/17
 */
public class Dp_cut {

    public static void main(String[] args) {
        int[] prices = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int n = 4;
        // TODO-QIU: 2024年12月23日, 0023 不懂
        System.out.println(cut(prices, n));
    }

    /**
     *
     * 动态规划通常用于解决最优问题，这类问题可以有很多可行解，每个解都有一个值，我们希望寻找具有最优值（最小值或最大值）的解。
     * 我们称这样的解为问题的一个最优解 (an optimal solution), 而不是最优解 (the optimal solution) , 因为可能有多个解都达到最优值。
     *
     * 我们通常按如下4个步骤来设计一个动态规划算法：
     * 1. 刻画一个最优解的结构特征。
     * 2. 递归地定义最优解的值。
     * 3. 计算最优解的值，通常采用自底向上的方法。
     * 4. 利用计算出的信息构造一个最优解。
     *
     * @param p
     * @param n
     * @return
     */
    private static int cut(int[] p, int n) {
        if (n == 0) {
            return 0;
        }

        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, p[i] + cut(p, n - i));
        }

        return q;
    }

}
