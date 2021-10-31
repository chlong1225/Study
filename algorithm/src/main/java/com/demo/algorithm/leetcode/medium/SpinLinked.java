package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2021/10/31.
 * description : 旋转链表
 *
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *  
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class SpinLinked {

    public static ListNode rotateRight(ListNode head, int k) {
        if (k == 0) {
            return head;
        }
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        //1,获取链表的长度
        int count = 0;
        while (p != null) {
            count++;
            p = p.next;
        }
        //2,获取旋转后的链表头
        int index = k % count;
        if (index == 0) {
            return head;
        }
        index = count - index - 1;
        ListNode pre = head;
        while (index > 0) {
            pre = pre.next;
            index--;
        }
        ListNode result = pre.next;
        pre.next = null;
        ListNode q = result;
        while (q.next != null) {
            q = q.next;
        }
        q.next = head;
        return result;
    }
}
