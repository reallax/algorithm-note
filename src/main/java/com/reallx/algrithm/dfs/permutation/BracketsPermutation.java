package com.reallx.algrithm.dfs.permutation;

import java.util.*;

/**
 *
 * 给出一个数字n，代表n对小括号组()，写出n对小括号的合法全排列。
 *
 * 比如n=3，合法全排列有：
 * ((())), (()()), ()()(), ()(()), (())()
 *
 * 做了几道深度优先算法，觉得合法括号排列也是一个可以用深度优先遍历来处理的问题，
 * 只需找到判断排列是否合法的条件即可
 *
 * 这里我们用栈的方式，在搜索时，如果遇到左括号(，则入栈，如果遇右括号，则出栈。
 * 如果遇到入栈右括号，但却不能出栈左括号的情况，判断为非法。
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/11
 */
public class BracketsPermutation {
    private static String leftBracket = "(";
    private static String rightBracket = ")";

    public static void main(String[] args) {
        int n = 3;
        List<List<String>> perms = bracketsPermutation(n);

        if (perms != null) {

            perms.stream().forEach(p -> {
                p.stream().forEach(System.out::print);
                System.out.println();
            });
        }

        System.out.println(perms.size());

    }

    public static List<List<String>> bracketsPermutation(int n) {
        if (n <=0) {
            return null;
        }

        String[] brackets = new String[2 * n];
        for (int i=0; i<n; i++) {
            brackets[i] = leftBracket;
            brackets[n + i] = rightBracket;
        }

        int len = brackets.length;
        List<List<String>> result = new ArrayList<>();
        boolean[] used = new boolean[len];
        Deque<String> path = new ArrayDeque<>();
        Deque<String> stack = new ArrayDeque<>();

        dfs(brackets, len, 0, path, used, stack, result);

        return result;
    }

    private static void dfs(String[] brackets, int len, int depth, Deque<String> path,
                            boolean[] used, Deque<String> stack, List<List<String>> result) {
        if (depth == len) {
            if (stack.isEmpty()) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i=0; i<len; i++) {
            /**
             * 剪枝处理，模拟L47
             */
            if (i > 0 && brackets[i].equals(brackets[i - 1]) && !used[i - 1]) {
                continue;
            }

            if (!used[i]) {
                if (brackets[i].equals(leftBracket)) {
                    stack.push(brackets[i]);
                } else {
                    String pop = stack.pollFirst();
//                    if (pop == null) {
//                        continue;
//                    }
//                    System.out.println("pop: " + stack.size());
                }

                path.addLast(brackets[i]);
                used[i] = true;

                dfs(brackets, len, depth + 1, path, used, stack, result);

                used[i] = false;
                path.pollLast();
//                if (head != null) stack.push(head);
            }
        }
    }

}
