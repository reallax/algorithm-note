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
//        String s = "25525511135";
//        String s = "1111";
        String s = "010010";

        L93_RestoreIpAddress l93 = new L93_RestoreIpAddress();
        List<String> result = l93.restoreIpAddresses(s);

        result.stream().forEach(System.out::println);
    }

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        int limit = 3;

        List<String> result = new ArrayList<>();
        // 此范围之外的，不能复原成一个IPV4
        if (len < 4 || len > 12) {
            return result;
        }

        Deque<Integer> path = new ArrayDeque<>();

        dfs(s, len, limit, 1,0, path, result);

        return result;
    }

    private void dfs(String s, int len, int limit,
                     int searchStart, int searchDepth,
                     Deque<Integer> path, List<String> result) {
        if (searchDepth == limit) {
            result.add(restoreIp(s, new ArrayList<>(path)));
            System.out.println(String.format("~ [searchIdx: %d ], 添加Path：%s", searchDepth, path));
            return;
        }

        for (int i=searchStart; i<=len; i++) {
            int lastStart = path.size() == 0 ? 0 : path.peekLast();
            if (i - lastStart > 3) {
                break;
            }
            // len - i 而不是 len -lastStart，是因为我们只搜索三个数，此时i还没有被压入path，lastStart是第二位
            // 我们应该计算第3位到最后的子串的长度
            if (searchDepth == limit - 1 && len - i > 3) {
                continue;
            }
            // 如果只有四位，第三个子位之后还应该留有子串
            if (i == len) {
                continue;
            }

            String sub = s.substring(lastStart, i);
            if (!checkSegment(sub)) {
                continue;
            }

            // 因为只搜寻3个.，所以得出前三个点后，第四个段的ip子段的合法性也要校验一下。需要优化
            if (searchDepth == limit - 1) {
                sub = s.substring(i);
                if (!checkSegment(sub)) {
                    continue;
                }
            }

            path.addLast(i);
            System.out.println(String.format("+ [i: %d, searchStart: %d, searchDepth: %d ], 递归前：%s", i, searchStart, searchDepth, path));

            dfs(s, len, limit,  i+ 1, searchDepth + 1, path, result);

            path.pollLast();
            System.out.println(String.format("- [i: %d, searchStart: %d, searchDepth: %d ], 递归后：%s", i, searchStart, searchDepth, path));
        }
    }

    private boolean checkSegment(String segment) {
        if (segment.length() > 1 && segment.startsWith("0")) {
            return false;
        }
        int val = Integer.valueOf(segment);
        if (val > 255) {
            return false;
        }
        return true;
    }

    private String restoreIp(String s, List<Integer> segs) {
        StringBuilder b = new StringBuilder();
        for (int i=0; i<segs.size(); i++) {
            int lastStart = i == 0 ? 0 : segs.get(i - 1);
            b.append(s.substring(lastStart, segs.get(i))).append(".");
        }
        b.append(s.substring(segs.get(segs.size() - 1), s.length()));
        return b.toString();
    }
}
