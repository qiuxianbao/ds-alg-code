package cn.thinkinjava.dsalg.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序遍历
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1, 3, 2]
 *
 * @author qiuxianbao
 * @date 2024/03/06
 */
public class Lc94_inOrderTraversal {

    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        n2.left = n3;
        TreeNode root = new TreeNode(1);
        root.right = n2;

        // 递归方式
        System.out.println(inOrderTraversal0(root));
        // 非递归方式
        System.out.println(inOrderTraversal(root));
        System.out.println(inOrderTraversal(null));
    }

    /**
     * 非递归方式，借助于栈
     *
     * 思路：
     * 返回值是List
     * 中序遍历，顺序是 左子树-》根-》右子树
     * 中序遍历首先访问的是左，所以首先找到最左的节结点。然后出栈，处理右子树（右子树不为空同样的逻辑要找最左结点）
     *
     * 定义一个 stack:Stack, result:List
     * 定义一个指针，从根开始，curr = root，一直找到最左结点
     *
     * while外层循环（出栈和右子树入栈），!s.isEmpty() || curr!= null
     * while内层循环，左子树结点依次入栈, 直到左子树为空，while(curr != null) curr = curr.left
     *
     * s.pop出栈, 添加到result中
     *
     * 然后处理右子树 curr = curr.right（可以简化成 根-》左子树）
     * 说明：没有右子树，curr=null，栈只要不为空继续出栈
     *
     * @param root
     * @return
     */
    private static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // 防止 root为 null，导致stack不为空，node.val为空报错
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (!stack.isEmpty() || curr != null) {
            // 一直找到最左结点
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            TreeNode node = stack.pop();
            result.add(node.val);

            // 处理右子树
            curr = node.right;
        }

        return result;
    }

    /**
     * 递归方式
     *
     * 思路：
     * 返回值是List
     * 中序遍历，顺序是左子树-》根-》右子树
     *
     * @param root
     * @return
     */
    private static List<Integer> inOrderTraversal0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private static void inOrder(TreeNode root, List<Integer> result) {
        // 结束条件
        if (root == null) {
            return;
        }

        inOrder(root.left, result);
        result.add(root.val);
        inOrder(root.right, result);
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
