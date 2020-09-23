package com.reallx.algrithm.tree;

/**
 *
 * <a href="https://leetcode-cn.com/problems/binary-tree-cameras/">968</a>
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/22
 */
public class L968_MinCameraCover {

    public static void main(String[] args) {
        // 构造一棵树： [0,0,null,0,0]
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);

        L968_MinCameraCover l968 = new L968_MinCameraCover();

        System.out.println(l968.minCameraCover(root));
    }

    /**
     * 状态0：未覆盖
     * 状态1：已覆盖
     * 状态2：已安装摄像头
     **/
    private int cameraCount = 0;
    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return cameraCount;
        }

        if (lrd(root) == 0) {
            cameraCount++;
        }

        return cameraCount;
    }

    // lrd：后续遍历
    private int lrd(TreeNode node) {
        if (node == null) {
            return 1;
        }

        int leftStatus = lrd(node.left);
        int rightStatus = lrd(node.right);

        // 左右节点，每边三种状态，共有n中组合：
        // [0, 0], [0, 1], [1, 0], [0, 2], [2, 0], [1, 1], [1, 2], [2, 1], [2, 2]

        // 左右未被覆盖完全，本节点需要安装
        if (leftStatus == 0 || rightStatus == 0) {
            cameraCount++;
            return 2;
        }

        // 左右节点只是被覆盖，本节点未被覆盖
        if (leftStatus == 1 && rightStatus == 1) {
            return 0;
        }

        // [1, 2], [2, 1], [2, 2]的组合，本节点已经被覆盖，无需装摄像头
        if (leftStatus + rightStatus >= 3) {
            return 1;
        }

        // 理论上，这个是一个不会被走到的分支，所以返回任何数都无影响
        return -1;
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
