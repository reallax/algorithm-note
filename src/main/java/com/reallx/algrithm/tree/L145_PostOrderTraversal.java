package com.reallx.algrithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
public class L145_PostOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        L145_PostOrderTraversal l144 = new L145_PostOrderTraversal();
        System.out.println(l144.postOrderTraversal(root));
    }


    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
//        recursion(root, result);
//        iteration(root, result);
        iteration2(root, result);

        return result;
    }

    private void recursion(TreeNode node, List<Integer> result) {
        if (node == null) return;

        recursion(node.left, result);
        recursion(node.right, result);

        // last process
        result.add(node.val);
    }

    /**
     * 参考：
     * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/die-dai-jie-fa-shi-jian-fu-za-du-onkong-jian-fu-za/
     *
     * 迭代逻辑同{@link L94_InOrderTraversal}，但：
     * 1、处理数据的顺序并非我们期望的顺序，这里只是取巧，最终输出了我们期望的顺序而已。因此这里也是在入栈的时候操作的节点，但是加在数组前。
     * 2、要从：根 -> 左 -> 右，变成 左 -> 右 -> 根，需要在迭代时先处理右节点
     *
     * @param root
     * @param result
     */
    private void iteration(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                result.add(0, cur.val);
                stack.push(cur);
                cur = cur.right;
            } else {
                cur = stack.pop();
                cur = cur.left;
            }
        }
    }

    private void iteration2(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode prev = null;
        while (root != null || !stack.empty()) {
            // push all left node to stack
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // left node is all in stack
            // pop last one
            TreeNode node = stack.pop();
            /**
             * 因为后续遍历顺序是：左 -> 右 -> 根
             * 从节点数据处理顺序上看，左右两个节点的数据都必须在根节点前被处理
             * 从迭代顺序上看，肯定是先到根，然后才能访问到左右节点
             *
             * 所以，找到左节点需要从根上过一次，找到右节点数据，又要从根上过一次。后续遍历迭代会两次经过根节点
             * 需要一个判断来终止第二次过根节点的逻辑，这里用了一个指正指向上次被处理过的数据
             * 因为是后续遍历，根节点的前置节点一定是右节点，所以第二次过根节点时，判断如果右节点已经被处理过了，那么就可以结束子树的遍历了
             */
            if (node.right == null || node.right == prev) {
                // operator node data
                result.add(node.val);
                // record last process node
                prev = node;
                // reset root to null, to continue pop node from stack
                root = null;
            }
            // right node is not null && right has not been processed
            else {
                // root node has been popped, but we have to process right node first,
                // so we push root node back again
                stack.push(node);
                root = node.right;
            }
        }
    }
}
