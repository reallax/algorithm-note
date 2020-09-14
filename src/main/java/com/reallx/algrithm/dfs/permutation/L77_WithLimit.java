package com.reallx.algrithm.dfs.permutation;

import com.reallx.algrithm.Main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * <a href="https://leetcode-cn.com/problems/combinations/">77</a>
 *
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/14
 */
public class L77_WithLimit {

    public static void main(String[] args) {
        int n = 7, k = 3;
        L77_WithLimit inst = new L77_WithLimit();
        List<List<Integer>> result = inst.combine(n, k);

        Main.printList(result);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 0 || k == 0) {
            return result;
        }

        Deque<Integer> path = new ArrayDeque<>();

        dfs(n, k, 1, path, result);

        return result;
    }

    private void dfs(int limit, int len, int startIdx, Deque<Integer> path, List<List<Integer>> result) {
        if (path.size() == len) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i=startIdx; i<=limit; i++) {
            path.addLast(i);

            dfs(limit, len, i + 1, path, result);

            path.pollLast();
        }
    }
}
