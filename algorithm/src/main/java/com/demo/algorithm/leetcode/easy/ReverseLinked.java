package com.demo.algorithm.leetcode.easy;

import com.demo.algorithm.leetcode.entity.ListNode;

import java.util.List;

/**
 * create by chenglong on 9/16/21
 * description : 反转链表
 *
 *  给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *  
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *  
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 */
public class ReverseLinked {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode result = null;
        ListNode pre;
        while (head != null) {
            pre = result;
            result = head;
            head = head.next;
            result.next = pre;
        }
        return result;
    }
}
