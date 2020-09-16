package com.reallx.algrithm.dfs.permutation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * <a herf="https://leetcode-cn.com/problems/permutation-sequence/">60</a>
 *
 * 此题必须剪枝，否则效率很低
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/15
 */
public class L60_OnePermutation {

    public static void main(String[] args) {
        int n = 3, k = 3;

        L60_OnePermutation l60 = new L60_OnePermutation();
        System.out.println(l60.getPermutation(n, k));
    }

    public String getPermutation(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[n];

        dfs(n, k, 0, path, used, result);

        StringBuilder b = new StringBuilder();
        for (Integer i:result.get(k - 1)) {
            b.append(i);
        }
        return b.toString();
    }

    private void dfs(int n, int k, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> result) {
        if (depth == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i=1; i<=n; i++) {
            if (used[i - 1]) {
                continue;
            }
            path.addLast(i);
            used[i - 1] = true;

            dfs(n, k, depth + 1, path, used, result);

            if (result.size() == k) {
                return;
            } else {
                used[i - 1] = false;
                path.pollLast();
            }
        }
    }
}
