package com.reallx.algrithm.tree;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <a href="https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/">106</a>
 *
 * 抄的答案……
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/25
 */
public class L106_BuildTree {



    private Map<Integer, Integer> inOrderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i=0; i<inorder.length; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }

        TreeNode root = buildTree(0, postorder.length - 1, 0, postorder.length - 1, postorder);
        return root;
    }

    public TreeNode buildTree(int is, int ie, int ps, int pe, int[] postorder) {
        if (ie < is || pe < ps) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[pe]);
        int ri = inOrderIndexMap.get(postorder[pe]);
        root.left = buildTree(is, ri - 1, ps, ps + ri - is - 1, postorder);
        root.right = buildTree(ri + 1, ie, ps + ri - is, pe - 1, postorder);

        return root;
    }
}
