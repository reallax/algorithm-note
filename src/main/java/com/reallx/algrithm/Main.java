package com.reallx.algrithm;

import java.util.List;

/**
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/7
 */
public class Main {



    public static void printList(List<List<Integer>> result) {
        result.stream().forEach(r -> {
            r.stream().forEach(s -> {
                System.out.print(s);
            });
            System.out.println();
        });
    }
}
