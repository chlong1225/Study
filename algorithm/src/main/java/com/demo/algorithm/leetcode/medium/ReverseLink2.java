package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2021/11/7.
 * description : 反转链表II
 *
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *  
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *  
 * 提示
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *  
 * 进阶： 你可以使用一趟扫描完成反转吗？
 */
public class ReverseLink2 {

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode p = head;
        ListNode start = p;
        int count = 1;
        while (count < left) {
            start = start.next;
            count++;
            if (count != left) {
                p = p.next;
            }
        }
        int reverse = right - left + 1;
        ListNode reverseNode = null;
        ListNode end = null;
        while (reverse > 0) {
            reverse--;
            if (reverseNode == null) {
                reverseNode = start;
                end = start;
                start = start.next;
                reverseNode.next = null;
            } else {
                ListNode tem = reverseNode;
                reverseNode = start;
                start = start.next;
                reverseNode.next = tem;
            }
        }
        if (left == 1) {
            p = reverseNode;
            end.next = start;
            return p;
        } else {
            p.next = reverseNode;
            end.next = start;
            return head;
        }
    }
}
