package com.demo.algorithm.leetcode.lcr;

import com.demo.algorithm.leetcode.entity.ListNode;
/**
 * create on 2023/12/27
 * @author chenglong
 * description : LCR 027. 回文链表
 *
 * 给定一个链表的头节点head，请判断其是否为回文链表。
 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
 *
 * 示例 1：
 * 输入: head = [1,2,3,3,2,1]
 * 输出: true
 *
 * 示例 2：
 * 输入: head = [1,2]
 * 输出: false
 *
 * 提示：
 * 链表L的长度范围为[1, 10^5]
 * 0 <= node.val <= 9
 */
public class PalindromeNode {

    public boolean isPalindrome(ListNode head) {
        //1，统计节点数量
        ListNode p = head;
        int count = 1;
        while (p.next != null) {
            count++;
            p = p.next;
        }
        if (count == 1) {
            return true;
        }
        //2，跳转到后后半段节点数
        int jumpCount = count / 2;
        if (count % 2 == 1) {
            jumpCount++;
        }
        ListNode lastHead = head;
        while (jumpCount > 0) {
            jumpCount--;
            lastHead = lastHead.next;
        }
        //3，从lastHead开始反转链表
        ListNode q = reverseList(lastHead);
        p = head;
        while (q != null) {
            if (q.val != p.val) {
                return false;
            }
            q = q.next;
            p = p.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode head) {
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
