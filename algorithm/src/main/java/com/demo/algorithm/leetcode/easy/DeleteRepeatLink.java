package com.demo.algorithm.leetcode.easy;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * create by chenglong on 9/6/21
 * description : 删除排序链表中的重复元素
 *
 *  存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 *  返回同样按升序排列的结果链表。
 *
 *  示例1
 *  输入：head = [1,1,2]
 *  输出：[1,2]
 *
 *  示例2
 *  输入：head = [1,1,2,3,3]
 *  输出：[1,2,3]
 *
 *
 */
public class DeleteRepeatLink {

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode result = new ListNode();
        ListNode start = result;
        start.next = head;
        start = start.next;
        while (head != null) {
            if (head.val != start.val) {
                start.next = head;
                start = start.next;
            }
            head = head.next;
        }
        if (start.next != null) {
            start.next = null;
        }
        return result.next;
    }
}
