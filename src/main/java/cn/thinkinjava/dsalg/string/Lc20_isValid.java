package cn.thinkinjava.dsalg.string;

import java.util.Stack;

/**
 * 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需要满足：
 * 1.左括号必须用相同类型的右括号闭合
 * 2.左括号必须以正确的顺序闭合
 * 3.注意空字符串可被认为是有效字符串
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 *
 * @author qiuxianbao
 * @date 2024/02/29
 */
public class Lc20_isValid {

    public static void main(String[] args) {
        String s = "{[]}";
//        String s = "([)]";
        System.out.println(isValid(s));
    }

    /**
     * 思路：
     * 返回值是boolean
     *
     * 这种匹配的操作可以借助栈操作
     *
     * 定义一个栈结构s: Stack
     * 循环字符str.toCharArray(),
     * 如果ch是 ({[，对应 )}] 入栈
     * 否则栈为empty，或者出栈pop和ch比对不相等为false。 stack.isEmpty || s.pop != ch
     *
     * 最后根据栈是否为空，返回 boolean
     *
     * @param s
     * @return
     */
    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != ch){
                return false;
            }
        }

        return stack.isEmpty();
    }
}
