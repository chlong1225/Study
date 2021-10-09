package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2021/10/4.
 * description : 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 *  
 * 提示：
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 */
public class SwapLinkedValue {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode p = head.next;
        swap(pre, p);
        while (p.next != null && p.next.next != null) {
            pre = p.next;
            p = pre.next;
            swap(pre, p);
        }
        return head;
    }

    private void swap(ListNode pre, ListNode p) {
        int tem = pre.val;
        pre.val = p.val;
        p.val = tem;
    }
}
