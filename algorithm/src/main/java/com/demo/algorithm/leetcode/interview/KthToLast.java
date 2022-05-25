package com.demo.algorithm.leetcode.interview;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2022/5/25.
 * description : 面试题02.02. 返回倒数第k个节点
 *
 * 实现一种算法，找出单向链表中倒数第k个节点。返回该节点的值。
 *
 * 注意：本题相对原题稍作改动
 * 示例：
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 * 说明：
 * 给定的k保证是有效的。
 */
public class KthToLast {

    public int kthToLast(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        while (k > 0) {
            k--;
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.val;
    }
}
