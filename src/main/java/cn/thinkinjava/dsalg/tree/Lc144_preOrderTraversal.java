package cn.thinkinjava.dsalg.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的先序遍历
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1, 2, 3]
 *
 * @author qiuxianbao
 * @date 2024/03/06
 */
public class Lc144_preOrderTraversal {

    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        n2.left = n3;
        TreeNode root = new TreeNode(1);
        root.right = n2;

        // 递归方式
        System.out.println(preOrderTraversal0(root));
        // 非递归方式
        System.out.println(preOrderTraversal(root));
        System.out.println(preOrderTraversal(null));
    }

    /**
     * 非递归方式，借助于栈
     *
     * 思路：
     * 返回值是List
     * 先序遍历，顺序是根-》左子树-》右子树
     * 第一个结点是根，所以根直接入栈，然后弹出，处理右和左入栈
     *
     * 定义 result：List， stack：Stack
     * 根入栈，stack.pus(root): 先序遍历第一个结点是根
     *
     * while循环栈不为空
     * 出栈，result.add()
     * 右节点不为空右入栈
     * 左节点不为空左入栈
     *
     * 最后返回list
     *
     * @param root
     * @return
     */
    private static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // 防止 root为 null，导致stack不为空，node.val为空报错
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }

    /**
     * 递归方式
     *
     * 思路：
     * 返回值是List
     * 先序遍历，顺序是根-》左子树-》右子树
     *
     * @param root
     * @return
     */
    private static List<Integer> preOrderTraversal0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private static void preOrder(TreeNode root, List<Integer> result) {
        // 结束条件
        if (root == null) {
            return;
        }

        result.add(root.val);

        preOrder(root.left, result);
        preOrder(root.right, result);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
