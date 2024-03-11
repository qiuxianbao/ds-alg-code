package cn.thinkinjava.dsalg.tree;

/**
 * 对称二叉树
 * 给给定一个二叉树，检查它是否是镜像对称的
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * @author qiuxianbao
 * @date 2024/03/11
 */
public class Lc101_IsSymmetric {

    public static void main(String[] args) {
        TreeNode nodeFour = new TreeNode(4);
        TreeNode nodeThree = new TreeNode(3);
        TreeNode nodeTwo = new TreeNode(2);
        nodeTwo.left = nodeFour;
        nodeTwo.right = nodeThree;

        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        node2.left = node3;
        node2.right = node4;

        TreeNode root = new TreeNode(1);
        root.left = node2;
        root.right = nodeTwo;

        System.out.println(isSymmetric(root));
    }


    /**
     * 思路：递归
     * 返回值是boolean
     *
     * 将当前结点的left和right分别当作2个二叉树进行递归，比较2边的结点是否对称（思路和一个二叉树是否是另一个二叉树的子树类似）
     * 2个结点的比较
     *
     * 判断是否镜像，比较左子树的左节点和右子树的右节点是否相同
     *
     * @param root
     * @return
     */
    private static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }

        return isSymmetric(root.left, root.right);
    }

    /**
     * 比较2棵子树是否对称
     *
     * @param t1
     * @param t2
     * @return
     */
    private static boolean isSymmetric(TreeNode t1, TreeNode t2) {
        // 循环结束条件
        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null || t1.val != t2.val) {
            return false;
        }

        return isSymmetric(t1.left, t2.right) && isSymmetric(t1.right, t2.left);
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
