package cn.thinkinjava.dsalg.tree;

/**
 * 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度
 *
 * 说明:
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数
 * 叶子节点是指没有子节点的节点
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *  /    \
 * 15     7
 * 返回它的最大深度 3 。
 *
 * @author qiuxianbao
 * @date 2024/03/15
 */
public class Lc104_MaxDepth {

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);
        TreeNode node20 = new TreeNode(20);
        node20.right = node7;

        TreeNode node15 = new TreeNode(15);
        TreeNode node9 = new TreeNode(9);
        node9.left = node15;

        TreeNode root = new TreeNode(3);
        root.left = node9;
        root.right = node20;

        System.out.println(maxDepth(root));
    }

    /**
     * 思路：递归
     * 返回值是int
     *
     * 先序遍历的思路，从叶子节点到根节点，每回退一次进行 +1 累加
     *
     * 递归的结束条件：if(root= null) return 0
     * 递归比较当前节点左子树和右子树的最大值： int left = 递归左子树， int right = 递归右子树
     * 得到left和right的值最大值之后，+1 弹栈
     *
     * @param root
     * @return
     */
    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        // 递归左右子树，弹栈前，+ 1
        return Math.max(left, right) + 1;
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
