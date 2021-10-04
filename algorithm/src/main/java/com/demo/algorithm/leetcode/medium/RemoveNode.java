package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2021/10/4.
 * description : 删除链表的倒数第 N 个结点
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 *
 * 输出：[1]
 *
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class RemoveNode {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        ListNode result = new ListNode();
        result.next = head;
        ListNode pre = result;
        ListNode fast = head;
        while (fast != null) {
            fast = fast.next;
            n--;
            if (n == 0) {
                break;
            }
        }
        while (fast != null) {
            fast = fast.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return result.next;
    }

}
