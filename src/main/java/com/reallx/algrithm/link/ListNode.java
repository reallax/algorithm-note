package com.reallx.algrithm.link;

/**
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/23
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    void print() {
        if (this == null) {
            return;
        }
        ListNode n = this;
        while(true) {
            System.out.print(n.val + ", ");
            n = n.next;
            if (n == null) {
                break;
            }
        }
    }
}
