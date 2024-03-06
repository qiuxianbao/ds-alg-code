package cn.thinkinjava.dsalg.tree;

/**
 * 说明: 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * 将其展开为：
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * @author qiuxianbao
 * @date 2024/03/06
 */
public class Lc114_flatten {

    public static void main(String[] args) {
        TreeNode n6 = new TreeNode(6);
        TreeNode n5 = new TreeNode(5);
        n5.right = n6;

        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);

        TreeNode n2 = new TreeNode(2);
        n2.left = n3;
        n3.right = n4;

        TreeNode root = new TreeNode(1);
        root.left = n2;
        root.right = n5;

        flatten(root);

        // 打印
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }

    /**
     * 思路：
     * 二叉树的展开为链表，顺序是先序遍历
     * 且右子树放在左子树的最右结点上
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     *
     *
     * 1、将root根节点的右子树，插在左子树的最右节点的右子树上
     *     1
     *    /
     *   2
     *  / \
     * 3   4
     *      \
     *       5
     *        \
     *         6
     *
     * 2、将root根节点的左子树插在root的右子树上，设置root的左子树为空
     *        1
     *      /  \
     *   null   2
     *         / \
     *        3   4
     *             \
     *              5
     *               \
     *                6
     *
     * 3、将root变为root.right，继续下一轮循环，直到root为空
     *        1
     *       / \
     *   null   2
     *         /
     *        3
     *         \
     *          4
     *           \
     *            5
     *             \
     *              6
     *
     *        1
     *       / \
     *   null   2
     *        /  \
     *     null   3
     *             \
     *              4
     *               \
     *                5
     *                 \
     *                  6
     *    ...
     *
     * while循环(root！=null)
     * 如果 root.left=null, 则直接处理右子树 root = root.right
     * 否则
     *      首先找到左子树的最右结点，定义 pre = root.left, while(pre!=null) pre=pre.right，得到最右结点
     *      pre.right = root.right
     *      root.right = root.left
     *      root.left = null
     *
     *      进行下一次迭代处理，root = root.right
     *
     * @param root
     */
    private static void flatten(TreeNode root) {
        while (root != null) {
            // 无左子树，一直是链表就不需要处理了
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树的最右结点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }

                // 将root根节点的右子树，插在左子树的最右节点的右子树上
                pre.right = root.right;
                // 将root根节点的左子树插在root的右子树上，设置root的左子树为空
                root.right = root.left;
                // 处理左节点
                root.left = null;
                // 继续下一轮
                root = root.right;
            }
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
