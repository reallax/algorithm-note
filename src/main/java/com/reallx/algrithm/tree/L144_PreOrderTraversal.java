package com.reallx.algrithm.tree;

import java.util.*;

/**
 * <a herf="https://leetcode-cn.com/problems/binary-tree-preorder-traversal/">144</a>
 *
 * two way for binary tree preorder traversal:
 * 1、recursion
 * 2、iteration
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/30
 */
public class L144_PreOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        L144_PreOrderTraversal l144 = new L144_PreOrderTraversal();
        System.out.println(l144.preorderTraversal(root));
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        //recursion(root, result);
//        iteration(root, result);
        iteration2(root, result);

        return result;
    }

    private void recursion(TreeNode node, List<Integer> result) {
        if (node == null) return;

        // first process
        result.add(node.val);

        recursion(node.left, result);
        recursion(node.right, result);
    }

    /**
     * references:
     * 1、https://mp.weixin.qq.com/s/c_zCrGHIVlBjUH_hJtghCg
     *
     * 前序遍历：处理顺序与迭代顺序一致，不需要指针
     * 中序遍历 + 后续遍历：处理顺序晚于迭代顺序，
     *
     * @param root
     * @param result
     */
    private void iteration(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (stack.size() > 0) {
            TreeNode node = stack.pop();
            result.add(node.val);

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

    /**
     * 带指针的前序遍历：
     * 1、每拿到一个节点，就把它保存在栈中
     * 2、继续对这个节点的左子树重复过程1，直到左子树为空
     * 3、因为保存在栈中的节点都遍历了左子树，但是没有遍历右子树，所以对栈中节点出栈，并对它的右子树重复过程1
     * 4、直到遍历完所有节点
     * 5、可能会有疑问，在什么地方处理节点数据？迭代顺序与处理顺序一致，所有可以直接在入栈时操作节点。（入栈的顺序与前序遍历的顺序一致，所以在入栈是处理数据）
     *
     * @param root
     * @param result
     */
    private void iteration2(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                // 迭代顺序与处理顺序一致，所有可以直接操作节点
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
    }
}
