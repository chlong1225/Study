package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2022/3/25.
 * description : 剑指Offer 24. 反转链表
 *
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = null;
        while (head != null) {
            ListNode tem = cur;
            cur = head;
            head = head.next;
            cur.next = tem;
        }
        return cur;
    }
}
