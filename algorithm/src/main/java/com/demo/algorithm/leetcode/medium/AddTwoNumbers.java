package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * create on 2023/7/3
 * @author chenglong
 * description : 两数相加II
 *
 * 给你两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字0之外，这两个数字都不会以零开头。
 *
 * 示例1：
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 *
 * 示例2：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 *
 * 示例3：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 提示：
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导0
 *
 * 进阶：如果输入链表不能翻转该如何解决？
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        //1，链表反转
        l1 = reverse(l1);
        l2 = reverse(l2);
        //2，链表相加
        ListNode root = new ListNode();
        ListNode p = root;
        int add = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + add;
            if (sum > 9) {
                sum -= 10;
                add = 1;
            } else {
                add = 0;
            }
            p.next = new ListNode(sum);
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null) {
            while (l2 != null) {
                int sum = l2.val + add;
                if (sum > 9) {
                    sum -= 10;
                    add = 1;
                } else {
                    add = 0;
                }
                p.next = new ListNode(sum);
                p = p.next;
                l2 = l2.next;
            }
        } else {
            while (l1 != null) {
                int sum = l1.val + add;
                if (sum > 9) {
                    sum -= 10;
                    add = 1;
                } else {
                    add = 0;
                }
                p.next = new ListNode(sum);
                p = p.next;
                l1 = l1.next;
            }
        }
        if (add > 0) {
            p.next = new ListNode(add);
        }
        return reverse(root.next);
    }

    //链表反转
    private ListNode reverse(ListNode l1) {
        ListNode p = l1;
        ListNode head = new ListNode(l1.val);
        while (p.next != null) {
            ListNode tem = new ListNode(p.next.val);
            p = p.next;
            tem.next = head;
            head = tem;
        }
        return head;
    }
}
