package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/7/31
 * @author chenglong
 * description : 重排链表
 *
 * 给定一个单链表L的头节点head，单链表L表示为：
 * L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *
 * 提示：
 * 链表的长度范围为 [1, 5 * 10^4]
 * 1 <= node.val <= 1000
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        List<ListNode> dates = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            ListNode tem = p;
            p = p.next;
            tem.next = null;
            dates.add(tem);
        }
        p = head;
        boolean isEnd = true;
        int start = 1;
        int end = dates.size() - 1;
        while (start <= end) {
            if (isEnd) {
                p.next = dates.get(end);
                end--;
                isEnd = false;
            } else {
                p.next = dates.get(start);
                start++;
                isEnd = true;
            }
            p = p.next;
        }
    }
}
