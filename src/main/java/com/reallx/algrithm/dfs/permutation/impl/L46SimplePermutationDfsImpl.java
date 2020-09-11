package com.reallx.algrithm.dfs.permutation.impl;

import com.reallx.algrithm.dfs.permutation.L46_SimplePermutation;

import java.util.*;

/**
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/7
 */
public class L46SimplePermutationDfsImpl implements L46_SimplePermutation {


    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);

        int len = array.size();
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> result = new ArrayList<>();

        dfs(array, len, 0, path, used, result);

        System.out.println("All permutation size: " + result.size());
        result.stream().forEach(r -> {
            r.stream().forEach(s -> {
                System.out.print(s);
            });
            System.out.println();
        });
    }

    private static void dfs(List<Integer> arrays, int len,
                            int depth, Deque<Integer> path,
                            boolean[] used, List<List<Integer>> result) {
        if (depth == len) {
            List<Integer> sub = new ArrayList<>();
            for (Iterator<Integer> itr = path.iterator(); itr.hasNext();) {
                sub.add(itr.next());
            }

            result.add(sub);
            return;
        }

        for (int i=0; i<len; i++) {
            if (!used[i]) {
                path.addLast(arrays.get(i));
                used[i] = true;

                dfs(arrays, len, depth + 1, path, used, result);

                used[i] = false;
                path.pollLast();
            }
        }
    }

}
