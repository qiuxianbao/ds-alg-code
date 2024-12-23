package cn.thinkinjava.dsalg.string;

/**
 * 斐波那契数列
 * <a href="https://blog.csdn.net/u013309870/article/details/75193592">算法-动态规划</a>
 *
 * 方案：
 * 动态规划、备忘录、递归
 *
 * @author qiuxianbao
 * @date 2024/12/17
 */
public class Dp_fibonacci {

    public static void main(String[] args) {
        System.out.println(fib(5));
        System.out.println(fib2(5));
        System.out.println(fib3(5));
        System.out.println(fib4(5));
    }


    /**
     * 自底向上，动态规划
     * 空间压缩
     *
     * @param n
     * @return
     */
    public static int fib4(int n) {
        if (n <= 1) {
            return n;
        }

        int memo_i_2 = 0;
        int memo_i_1 = 1;
        int memo_i = 0;
        for (int i = 2; i <= n; i++) {
            memo_i = memo_i_1 + memo_i_2;
            memo_i_2 = memo_i_1;
            memo_i_1 = memo_i;
        }

        return memo_i;
    }


    /**
     * 自底向上，动态规划
     * 先计算子问题，再由子问题计算父问题
     * 缺点：实际上参与计算的只有 i, i - 1 和 i - 2，所以可以用变量进行替换
     *
     * @param n
     * @return
     */
    public static int fib3(int n) {
        if (n <= 0) {
            return n;
        }

        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }


    /**
     * 自顶向下，备忘录模式
     * 将计算结果保存在Memo数组中，这样在调用fib（n）的时候就不会重新递归了
     *
     * @param n
     * @return
     */
    public static int fib2(int n) {
        if (n <= 0) {
            return 0;
        }

        // 初始化为 n+1，避免数组越界
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }

        return fib_memo(n, memo);
    }


    private static int fib_memo(int n, int[] memo) {
        if (memo[n] != -1) {
            return memo[n];
        }

        if (n <= 2) {
            memo[n] = 1;
        } else {
            memo[n] = fib_memo(n - 1, memo) + fib_memo(n - 2, memo);
        }

        return memo[n];
    }


    /**
     * 递归方式
     *
     * 缺点：存在多个节点被重复计算，重复递归
     *
     * 以 n=6 位例，fib(2)被重复计算了5次
     *
     * 								    fib(6)
     * 		    		fib(5)							fib(4)
     * 	    	fib(4)			fib(3)			fib(3)			fib(2)
     *  	fib(3)	fib(2)	fib(2)	fib(1)	fib(2)	fib(1)
     * fib(2)	fib(1)
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }
}
