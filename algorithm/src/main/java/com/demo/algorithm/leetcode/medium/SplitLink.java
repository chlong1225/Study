package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2021/11/7.
 * description : 分隔链表
 *
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
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
public class SplitLink {

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode data = head;
        //比x小
        ListNode small = new ListNode();
        ListNode big = new ListNode();
        ListNode p = small;
        ListNode q = big;
        while (data != null) {
            if (data.val < x) {
                p.next = data;
                data = data.next;
                p = p.next;
                p.next = null;
            } else {
                q.next = data;
                data = data.next;
                q = q.next;
                q.next = null;
            }
        }
        p.next = big.next;
        return small.next;
    }
}
