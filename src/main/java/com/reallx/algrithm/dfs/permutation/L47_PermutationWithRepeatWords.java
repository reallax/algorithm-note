package com.reallx.algrithm.dfs.permutation;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 题目参考：<a href="https://leetcode-cn.com/problems/permutations-ii/">47</a>
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/11
 */
public class L47_PermutationWithRepeatWords {


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1};
        Arrays.sort(nums);
        List<List<Integer>> result = permuteUnique(nums);

        result.stream().forEach(r -> {
            r.stream().forEach(s -> {
                System.out.print(s);
            });
            System.out.println();
        });

    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        dfs(nums, len, 0, path, used, result);
        return result;
    }

    private static void dfs(int[] nums, int len, int depth, Deque<Integer> path,
                            boolean[] used, List<List<Integer>> result) {
        if (depth == len) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i=0; i<len; i++) {
            int cur = nums[i];

            if (used[i]) {
                continue;
            }

            /**
             * 重复项剪枝
             * 1、当前值与上次搜索根节点相同，跳过这一枝的搜索
             * 2、当前值与上次搜索根节点相同，且上个相同值不在搜索路径中，跳过这一枝的搜索（如果相同值还在搜索路径中，长度变化也是不同的排列）
             * 3、这项剪枝排列有一个前提，数组本身有序。（所以开始要自己手动拍个序）
             */
            if (i>0 && cur == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(cur);
            used[i] = true;

            dfs(nums, len, depth + 1, path, used, result);

            used[i] = false;
            path.pollLast();
        }

    }
}
