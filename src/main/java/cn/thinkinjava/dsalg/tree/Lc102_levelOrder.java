package cn.thinkinjava.dsalg.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * @author qiuxianbao
 * @date 2024/03/06
 */
public class Lc102_levelOrder {

    public static void main(String[] args) {
        TreeNode n7 = new TreeNode(7);
        TreeNode n15 = new TreeNode(15);

        TreeNode n20 = new TreeNode(20);
        n20.left = n15;
        n20.right = n7;

        TreeNode n9 = new TreeNode(9);
        TreeNode root = new TreeNode(3);
        root.left = n9;
        root.right = n20;

        System.out.println(levelOrder(root));
    }

    /**
     * 思路：
     * 返回值List<List<Integer>>
     *
     * 借助于队列，第一个结点是根，根结点直接入队
     * 只要队列不为空，
     * while循环，出队一个结点，然后将出的这个结点的左、右结点分别入队
     *
     * 还要解决一个层次的问题，定义一个变量 size = queue.size()用来控制 while 循环
     * 用来控制一层的出结点个数，每出一个结点，size--
     *
     * 定义result、queue
     * 初始化，根节点入队
     *
     * while循环队列不为空
     * size=queue.size （一层结点的个数）
     *
     * while(size-- > 0)
     * 从队列种取出一个结点node
     * 如果node的左节点不为空、左节点入队
     * 如果node的右节点不为空，右节点入队
     *
     * 最后返回 result
     * @param root
     * @return
     */
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

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
