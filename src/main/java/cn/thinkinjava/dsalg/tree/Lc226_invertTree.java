package cn.thinkinjava.dsalg.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 翻转二叉树
 *
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 输出
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * @author qiuxianbao
 * @date 2024/03/11
 */
public class Lc226_invertTree {

    public static void main(String[] args) {
        TreeNode n9 = new TreeNode(9);
        TreeNode n6 = new TreeNode(6);

        TreeNode n7 = new TreeNode(7);
        n7.left = n6;
        n7.right = n9;

        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        n2.left = n1;
        n2.right = n3;

        TreeNode root = new TreeNode(4);
        root.left = n2;
        root.right = n7;

        System.out.println(Helper.levelOrder(root));

        invertTree(root);
        System.out.println(Helper.levelOrder(root));
    }


    /**
     * 思路： 递归
     *
     * 先序遍历，遇到节点，交换左右子树
     * 递归左子树，递归右子树
     * 结束条件是结点为空，即root = null
     *
     * @param root
     * @return
     */
    private static void invertTree(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
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
