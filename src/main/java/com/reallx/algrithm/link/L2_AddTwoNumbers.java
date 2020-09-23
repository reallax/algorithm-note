package com.reallx.algrithm.link;

/**
 * <a href="https://leetcode-cn.com/problems/add-two-numbers/">2</a>
 *
 * @author liuh
 * @email hong.liu@dmall.com
 * @date 2020/9/23
 */
public class L2_AddTwoNumbers {

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(5);
//        ListNode l2 = new ListNode(5);

//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(9);
//        l2.next = new ListNode(9);

        ListNode l1 = new ListNode(8);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(2);

        L2_AddTwoNumbers let2 = new L2_AddTwoNumbers();
        ListNode newNode = let2.addTwoNumbers(l1, l2);

        newNode.print();
    }


    /**
     * 问题在于每一次相加都有可能引起进位，要一直处理进位边界问题
     * 退出循环后，如果任何一个链表的next为null，如果不能要到父节点，子节点无论赋什么值，都是一个断链
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode curr = result;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return result.next;
    }
}
