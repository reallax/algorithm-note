package com.reallx.algrithm.tree;

/**
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/23
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }

    void print() {
        print(this);
    }

    void print(TreeNode tree) {
        if (tree == null) {
            return;
        }
        print(tree.left);
        System.out.print(tree.val + ",\t");
        print(tree.right);
    }
}
