package com.reallx.algrithm.dfs.permutation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/15
 */
public class L60 {

    public static void main(String[] args) {
        int n = 3, k = 3;

        L60 l60 = new L60();
        System.out.println(l60.getPermutation(n, k));
    }

    public String getPermutation(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();

        dfs(n, k, 1, path, result);

        StringBuilder b = new StringBuilder();
        for (Integer i:result.get(k - 1)) {
            b.append(i);
        }
        return b.toString();
    }

    private void dfs(int n, int k, int depth, Deque<Integer> path, List<List<Integer>> result) {
        if (depth == n + 1) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i=depth; i<=n; i++) {
            path.addLast(i);

            dfs(n, k, depth + 1, path, result);

            path.pollLast();

            if (result.size() == k) {
                return;
            }
        }
    }
}
