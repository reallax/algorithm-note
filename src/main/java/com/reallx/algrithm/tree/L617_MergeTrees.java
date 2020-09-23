package com.reallx.algrithm.tree;

/**
 * <a href="https://leetcode-cn.com/problems/merge-two-binary-trees/">617</a>
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/23
 */
public class L617_MergeTrees {

    public static void main(String[] args) {

        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3);
        t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(5);

        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(4);
        t2.right.right = new TreeNode(7);

        L617_MergeTrees l617 = new L617_MergeTrees();

        TreeNode merged = l617.mergeTrees(t1, t2);
        merged.print();
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        TreeNode leftNode = mergeTrees(t1.left, t2.left);
        TreeNode rightNode = mergeTrees(t1.right, t2.right);
        TreeNode parentNode = new TreeNode(t1.val + t2.val);
        parentNode.left = leftNode;
        parentNode.right = rightNode;
        return parentNode;
    }
}
