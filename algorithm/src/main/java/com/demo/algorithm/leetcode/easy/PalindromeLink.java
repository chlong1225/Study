package com.demo.algorithm.leetcode.easy;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * create on 9/27/21
 * @author chenglong
 * description : 回文链表
 *
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 *  
 * 提示：
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class PalindromeLink {

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        //1，查找链表中间节点
        ListNode slow = head;
        ListNode fast = head.next;
        int count = 1;
        if (fast == null) {
            return true;
        }
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            count++;
        }
        ListNode middle = slow.next;
        if (fast == null) {
            count--;
        }
        //2，将链表后半节反转
        ListNode result = null;
        ListNode tem = null;
        while (middle != null) {
            if (result != null) {
                tem = result;
            }
            result = middle;
            middle = middle.next;
            result.next = tem;
        }
        //3，对比两段链表
        for (int i = 0; i < count; i++) {
            if (head.val != result.val) {
                return false;
            }
            head = head.next;
            result = result.next;
        }
        return true;
    }
}
