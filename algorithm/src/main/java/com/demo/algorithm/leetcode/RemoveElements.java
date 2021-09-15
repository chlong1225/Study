package com.demo.algorithm.leetcode;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * create by chenglong on 9/15/21
 * description : 移除链表元素
 *
 *  给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *  
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *  
 * 提示：
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 */
public class RemoveElements {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode result = new ListNode();
        ListNode p = result;
        while (head != null) {
            if (head.val != val) {
                p.next = head;
                p = p.next;
            } else {
                p.next = head.next;
            }
            head = head.next;
        }
        return result.next;
    }

}
