package com.reallx.algrithm.dfs.permutation;

import com.reallx.algrithm.Main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * <a herf="https://leetcode-cn.com/problems/subsets/">78</a>
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/15
 */
public class L78_Subset {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};

        L78_Subset l78 = new L78_Subset();
        List<List<Integer>> result = l78.subsets(nums);

        Main.printList(result);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Deque<Integer> path = new ArrayDeque<>();

        dfs(nums, nums.length, 0, path, result);

        return result;
    }

    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int i=depth; i<len; i++) {
            path.addLast(nums[i]);

            dfs(nums, len, i + 1, path, result);

            path.pollLast();
        }
    }
}
