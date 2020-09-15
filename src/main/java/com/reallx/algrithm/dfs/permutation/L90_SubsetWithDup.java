package com.reallx.algrithm.dfs.permutation;

import com.reallx.algrithm.Main;

import java.util.*;

/**
 * <a href="https://leetcode-cn.com/problems/subsets-ii/">90</a>
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/15
 */
public class L90_SubsetWithDup {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 2};

        L90_SubsetWithDup l90 = new L90_SubsetWithDup();
        List<List<Integer>> result = l90.subsetsWithDup(nums);

        Main.printList(result);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];

        Arrays.sort(nums);
        dsf(nums, nums.length, 0, path, used, result);

        return result;
    }

    private void dsf(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));

        for (int i=depth; i<len; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;

            dsf(nums, len, i + 1, path, used, result);

            used[i] = false;
            path.pollLast();
        }
    }
}
