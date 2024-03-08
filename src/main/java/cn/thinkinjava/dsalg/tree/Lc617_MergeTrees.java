package cn.thinkinjava.dsalg.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 合并二叉树
 *
 * 将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。
 *
 * 合并的规则是：如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 示例 1:
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 *
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 *
 * @author qiuxianbao
 * @date 2024/03/08
 */
public class Lc617_MergeTrees {

    public static void main(String[] args) {
        // t1
        TreeNode t1n5 = new TreeNode(5);
        TreeNode t1n2 = new TreeNode(2);
        TreeNode t1n3 = new TreeNode(3);
        t1n3.left = t1n5;
        TreeNode t1 = new TreeNode(1);
        t1.left = t1n3;
        t1.right = t1n2;

        // t2
        TreeNode t2n7 = new TreeNode(7);
        TreeNode t2n3 = new TreeNode(3);
        t2n3.right = t2n7;
        TreeNode t2n4 = new TreeNode(4);
        TreeNode t2n1 = new TreeNode(1);
        t2n1.right = t2n4;
        TreeNode t2 = new TreeNode(2);
        t2.left = t2n1;
        t2.right = t2n3;

        System.out.println(Helper.levelOrder(mergeTrees(t1, t2)));
    }


    /**
     * 思路：
     * 返回值是TreeNode
     * 合并2个二叉树， 将t2覆盖到t1上，直接返回t1
     *
     * 递归 2 棵树，处理2个结点
     * 结束条件：至少一个为空，则用另一个结点来返回（覆盖)，即 if t1 == null return t2, if t2 == null return t1
     *
     * 否则2个节点都不为空，直接累加 t1.val += t2.val
     *
     * 分别递归处理左子树和右子树
     *      t1.left  = (t1.left, t2.left)
     *      t1.right = (t1.right, t2.right)
     *
     * @param t1
     * @param t2
     * @return
     */
    private static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // 为空覆盖操作
        if (t1 == null) {
            return t2;
        }

        if (t2 == null) {
            return t1;
        }

        t1.val += t2.val;

        // 递归处理左右子树
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }


    static class Helper {

        private static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            // LinkedList也是一个队列
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                // size控制一层结点的个数
                int size = queue.size();
                List<Integer> list = new ArrayList<>();
                while (size-- > 0) {
                    TreeNode node = queue.poll();
                    list.add(node.val);

                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                result.add(list);
            }

            return result;
        }
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
