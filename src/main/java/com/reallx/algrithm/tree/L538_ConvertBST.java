package com.reallx.algrithm.tree;

/**
 *
 * <a herf="https://leetcode-cn.com/problems/convert-bst-to-greater-tree/">538</a>
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/21
 */
public class L538_ConvertBST {

    int sum = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(13);
        root.left = new TreeNode(2);


        L538_ConvertBST l538 = new L538_ConvertBST();
        System.out.print("原树：");
        l538.print(root);

        TreeNode newTree = l538.convertBST(root);
        System.out.print("\n新树：");
        l538.print(newTree);
    }

    private void print(TreeNode tree) {
        if (tree == null) {
            return;
        }
        print(tree.left);
        System.out.print(tree.val + ",\t");
        print(tree.right);
    }

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }

        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);

        return root;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
