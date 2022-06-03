package com.demo.algorithm.leetcode.interview;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2022/6/3.
 * description : 面试题02.04. 分割链表
 *
 * 给你一个链表的头节点head和一个特定值x，请你对链表进行分隔，使得所有小于x的节点都出现在大于或等于x的节点之前。
 * 你不需要保留每个分区中各节点的初始相对位置。
 *
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 *
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class Partition {

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode root = new ListNode();
        ListNode big = new ListNode();
        ListNode p = root;
        ListNode q = big;
        while (head != null) {
            int cur = head.val;
            ListNode tem = head;
            head = head.next;
            if (cur < x) {
                p.next = tem;
                p = p.next;
                p.next = null;
            } else {
                q.next = tem;
                q = q.next;
                q.next = null;
            }
        }
        p.next = big.next;
        return root.next;
    }
}
