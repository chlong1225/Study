package com.demo.algorithm.leetcode.hard;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * create on 10/8/21
 * @author chenglong
 * description : 合并K个升序链表
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 *  
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 */
public class MergeLinked {

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode();
        if (lists == null || lists.length == 0) {
            return head.next;
        }
        ListNode p = head;
        int length = lists.length;
        int count = 0;
        int minIndex = -1;
        boolean[] isEmptys = new boolean[length];
        ListNode min = null;
        while (count < length) {
            for (int i = 0; i < length; i++) {
                if (lists[i] == null) {
                    if (!isEmptys[i]) {
                        isEmptys[i] = true;
                        count++;
                    }
                } else {
                    if (min == null) {
                        min = lists[i];
                        minIndex = i;
                    } else {
                        if (min.val > lists[i].val) {
                            min = lists[i];
                            minIndex = i;
                        }
                    }
                }
            }
            if (minIndex != -1 && lists[minIndex] != null) {
                lists[minIndex] = lists[minIndex].next;
            }
            if (min != null) {
                min.next = null;
            }
            p.next = min;
            p = p.next;
            min = null;
        }
        return head.next;
    }
}
