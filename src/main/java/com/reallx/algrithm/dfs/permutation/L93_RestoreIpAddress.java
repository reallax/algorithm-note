package com.reallx.algrithm.dfs.permutation;

import com.reallx.algrithm.Main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/16
 */
public class L93_RestoreIpAddress {

    public static void main(String[] args) {
        String s = "25525511135";

        L93_RestoreIpAddress l93 = new L93_RestoreIpAddress();
        List<List<Integer>> result = l93.restoreIpAddresses(s);

        Main.printList(result);
    }

    public List<List<Integer>> restoreIpAddresses(String s) {
        int len = s.length();
        int limit = 3;

        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();

        dfs(s, len, limit, 1, path, result);

        return result;
    }

    private void dfs(String s, int len, int limit, int searchIdx, Deque<Integer> path, List<List<Integer>> result) {
        if (searchIdx == limit + 1) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i=searchIdx; i<len; i++) {
            int subStart = path.size() == 0 ? 0 : path.peekLast();
            if (i - searchIdx > 3) {
                continue;
            }

            String sub = s.substring(subStart, i);
            int val = Integer.valueOf(sub);
            if (val > 255) {
                continue;
            }

            path.addLast(i);

            dfs(s, len, limit, searchIdx + 1, path, result);

            path.pollLast();
        }
    }
}
