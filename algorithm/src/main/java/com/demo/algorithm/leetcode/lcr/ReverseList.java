package com.demo.algorithm.leetcode.lcr;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * create on 2023/12/27
 * @author chenglong
 * description : LCR 024. 反转链表
 *
 * 给定单链表的头节点head，请反转链表，并返回反转后的链表的头节点。
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
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode p = head.next;
        first.next = null;
        while (p != null) {
            //此时first与p进行拼接
            ListNode tem = p;
            p = p.next;
            tem.next = first;
            first = tem;
        }
        return first;
    }
}
