package com.reallx.algrithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <a herf="https://leetcode-cn.com/problems/binary-tree-inorder-traversal//">94</a>
 *
 * two way traversal:
 * 1、recursion
 * 2、iteration
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/30
 */
public class L94_InOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        L94_InOrderTraversal l144 = new L94_InOrderTraversal();
        System.out.println(l144.inorderTraversal(root));
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
//        recursion(root, result);
        iteration(root, result);

        return result;
    }

    private void recursion(TreeNode node, List<Integer> result) {
        if (node == null) return;

        recursion(node.left, result);

        // middle process
        result.add(node.val);

        recursion(node.right, result);
    }

    /**
     *
     * 遍历逻辑，同{@link L144_PreOrderTraversal}，但：
     * 1、出栈的顺序与中序遍历的顺序一致，所以在出栈的时候处理
     *
     * @param root
     * @param result
     */
    private void iteration(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
    }
}
