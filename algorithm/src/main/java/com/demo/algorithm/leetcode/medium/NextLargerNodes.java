package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * create on 2023/4/10
 * @author chenglong
 * description : 链表中的下一个更大节点
 *
 * 给定一个长度为n的链表head
 * 对于列表中的每个节点，查找下一个更大节点的值。也就是说对于每个节点，找到它旁边的第一个节点的值，这个节点的值严格大于它的值。
 * 返回一个整数数组answer，其中answer[i]是第i个节点(从1开始)的下一个更大的节点的值。如果第i个节点没有下一个更大的节点，设置answer[i]=0。
 *
 * 示例 1：
 * 输入：head = [2,1,5]
 * 输出：[5,5,0]
 *
 * 示例 2：
 * 输入：head = [2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 *
 * 提示：
 * 链表中节点数为n
 * 1 <= n <= 10^4
 * 1 <= Node.val <= 10^9
 */
public class NextLargerNodes {

    public int[] nextLargerNodes(ListNode head) {
        //1，获取节点数量
        int count = 1;
        ListNode p = head.next;
        while (p != null) {
            count++;
            p = p.next;
        }
        int[] result = new int[count];
        if (count == 1) {
            return result;
        }
        //2，查找首个节点的结果
        ListNode cur = head;
        ListNode next = head.next;
        while (next != null) {
            if (cur.val < next.val) {
                break;
            }
            next = next.next;
        }
        if (next != null) {
            result[0] = next.val;
        }

        ListNode pre = cur;
        for (int i = 1; i < count; i++) {
            cur = cur.next;
            //获取当前节点的结果
            if (cur.val == pre.val) {
                result[i] = result[i - 1];
            } else {
                next = cur.next;
                while (next != null) {
                    if (cur.val < next.val) {
                        break;
                    }
                    next = next.next;
                }
                if (next != null) {
                    result[i] = next.val;
                }
            }
            pre = cur;
        }
        return result;
    }
}
