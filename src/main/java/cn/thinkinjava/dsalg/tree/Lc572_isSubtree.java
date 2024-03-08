package cn.thinkinjava.dsalg.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断二叉树是否另一棵二叉树的子树
 *
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。
 * 如果存在，返回 true ；否则，返回 false 。
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *
 * 示例 1：
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 *
 * @author qiuxianbao
 * @date 2024/03/08
 */
public class Lc572_isSubtree {

    public static void main(String[] args) {
        TreeNode s0 = new TreeNode(0);
        TreeNode s2 = new TreeNode(2);
        s2.right = s0;

        TreeNode s1 = new TreeNode(1);
        TreeNode s4 = new TreeNode(4);
        s4.left = s1;
        s4.right = s2;

        TreeNode s5 = new TreeNode(5);
        TreeNode s = new TreeNode(3);
        s.left = s4;
        s.right = s5;

        System.out.println("root is " + Helper.preOrderTraversal(s));


        TreeNode t2 = new TreeNode(2);
        TreeNode t1 = new TreeNode(1);

        TreeNode t = new TreeNode(4);
        t.left = t1;
        t.right = t2;

        System.out.println("subRoot is " + Helper.preOrderTraversal(t));

        System.out.println(isSubtree(s, t));

    }

    /**
     * 思路：
     *
     * 要在s树中找和t树结点相同的结点
     *
     * 首先要在s中找到和t结点相同的节点（s和t比，如果找不到，则需要s.left和t，s.right和t比），这是一个递归，结束条件是 if(s == null) return false
     * 第二个问题，比较（也是一个递归），结束条件是if(s==null && t==null) return true;
     *  剩下的条件分几种情况：
     *      其中有一个循环结束
     *      当前值不相同
     *      当前值相等，需要继续比较各自的左子树和右子树
     *
     * @param s
     * @param t
     * @return
     */
    private static boolean isSubtree(TreeNode s, TreeNode t) {
        // 递归结束条件
        if (s == null) {
            return false;
        }

        // 要在s中找到和t结点相同的节点
        // 如果当前2个结点不相等，那么就需要用s.left和t比，一直比到s.left=null（返回false）。还不相等，就用s.right和t比
        return check(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    /**
     * 检查当前2个子树是否相同
     *
     * 如果相等，（3）检查各自的左右子树结点，
     * 一直到（1）各自都是null（返回true）
     * （2）不等的情况：其中有一个为null或者2个值不相等（返回false）
     *
     * @param s
     * @param t
     */
    private static boolean check(TreeNode s, TreeNode t) {
        // 递归结束条件
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null || s.val != t.val) {
            return false;
        }

        // s.val = t.val
        return check(s.left, t.left) && check(s.right, t.right);
    }


    static class Helper {

        private static List<Integer> preOrderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            preOrder(root, result);
            return result;
        }

        private static void preOrder(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }

            result.add(root.val);
            preOrder(root.left, result);
            preOrder(root.right, result);
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
