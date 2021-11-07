package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2021/11/7.
 * description : 删除排序链表中的重复元素II
 *
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * 返回同样按升序排列的结果链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 *  
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class RemoveRepeatLink2 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        ListNode result = new ListNode();
        ListNode q = result;
        ListNode tem = p;
        int count = 1;
        while (p.next != null) {
            p = p.next;
            if (tem.val == p.val) {
                count++;
            } else {
                if (count == 1) {
                    q.next = tem;
                    q = q.next;
                }
                tem = p;
                count = 1;
            }
        }
        if (count == 1) {
            q.next = tem;
            q = q.next;
        }
        q.next = null;
        return result.next;
    }
}
