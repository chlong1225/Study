package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer52. 两个链表的第一个公共节点
 *
 */
public class FindIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p = headA;
        ListNode q = headB;
        int count = 0;
        while (true) {
            if (p == q) {
                return p;
            }
            p = p.next;
            q = q.next;
            if (p == null) {
                p = headB;
                count++;
                if (count == 2) {
                    return null;
                }
            }
            if (q == null) {
                q = headA;
            }
        }
    }
}
