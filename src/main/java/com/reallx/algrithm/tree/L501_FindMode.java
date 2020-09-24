package com.reallx.algrithm.tree;

import java.util.*;

/**
 * <a href="https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/">501</a>
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/24
 */
public class L501_FindMode {

    /**
     * 期望结果 [1, 2]
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);

        L501_FindMode l501 = new L501_FindMode();
        int[] result = l501.findMode(root);

        Arrays.stream(result).forEach(i -> System.out.print(i + ", "));

    }

    private Map<Integer, Integer> countMap = new HashMap<>();
    private int maxCount = 0;
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }

        ldr(root);

        if (countMap.size() == 1) {
            return new int[]{root.val};
        }

        List<Integer> modes = new ArrayList<>();
        for (Integer key:countMap.keySet()) {
            if (countMap.get(key).equals(maxCount)) {
                modes.add(key);
            }
        }

        int[] modeArr = new int[modes.size()];
        for (int i=0; i<modes.size(); i++) {
            modeArr[i] = modes.get(i);
        }



        return modeArr;
    }

    private void ldr(TreeNode root) {
        if (root == null) {
            return;
        }

        ldr(root.left);

        Integer count = countMap.get(root.val);
        if (count == null) {
            count = 0;
            countMap.put(root.val, count);
        }
        countMap.put(root.val, ++count);
        if (count > maxCount) {
            maxCount = count;
        }

        ldr(root.right);
    }
}
