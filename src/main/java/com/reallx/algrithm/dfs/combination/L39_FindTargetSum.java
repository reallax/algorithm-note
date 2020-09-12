package com.reallx.algrithm.dfs.combination;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * reference: <a href="https://leetcode-cn.com/problems/combination-sum/">39</a>
 *
 * 深度优先遍历：
 * 1、记录搜索路径
 * 2、找到搜索终止条件
 * 3、分析会不会出现排列重复，及规避条件
 * 4、递归次数小于100（大概）
 * 5、有时预排序，能简化算法
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/12
 */
public class L39_FindTargetSum {

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

        if (candidates == null || candidates.length == 0) {
            return result;
        }

        dfs(target, candidates, candidates.length, 0, path, result);

        return result;
    }

    public static void dfs(int target, int[] candidates, int len, int idx,
                           Deque<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        if (target < 0) {
            return;
        }

        /**
         * 避免产生无序重复组合（如：[2, 3, 2], [2, 2, 3]），同级搜索，跳过已搜索过的数，从idx开始
         */
        for (int i=idx; i<len; i++) {
            path.addLast(candidates[i]);

            dfs(target - candidates[i], candidates, len, i, path, result);

            path.pollLast();
        }
    }
}
