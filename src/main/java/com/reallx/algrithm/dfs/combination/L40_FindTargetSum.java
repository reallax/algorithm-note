package com.reallx.algrithm.dfs.combination;

import java.util.*;

/**
 *
 * reference: <a href="https://leetcode-cn.com/problems/combination-sum-ii/">40</a>
 *
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/12
 */
public class L40_FindTargetSum {

    public static void main(String[] args) {

        int[] candidates = new int[] {2, 2, 3, 5};
        int target = 8;

        List<List<Integer>> result = combinationSum(candidates, target);

        result.stream().forEach(r -> {
            r.stream().forEach(s -> {
                System.out.print(s);
            });
            System.out.println();
        });
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[candidates.length];

        Arrays.sort(candidates);
        backTrace(candidates, 0, target, used, path, result);

        return result;
    }

    private static void backTrace(int[] candidates, int searchIdx, int target, boolean[] used, Deque<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) {
            return;
        }
        /**
         * 避免重复，i从searchIdx开始
         */
        for (int i= searchIdx; i<candidates.length; i++) {
            /**
             * 避免重复，剪枝
             * 首先保证数组有序
             * 其次用used数组记录那些数已经使用过
             * !used[i - 1]，上一个搜索路径中无此值
             */
            if (i>0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            path.addLast(candidates[i]);
            used[i] = true;

            backTrace(candidates, i + 1, target - candidates[i], used, path, result);

            used[i] = false;
            path.pollLast();
        }
    }
}
