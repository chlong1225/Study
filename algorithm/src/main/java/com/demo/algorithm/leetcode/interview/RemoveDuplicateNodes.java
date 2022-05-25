package com.demo.algorithm.leetcode.interview;

import com.demo.algorithm.leetcode.entity.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chl on 2022/5/25.
 * description : 面试题02.01. 移除重复节点
 *
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 * 示例1:
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 *
 * 示例2:
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 * 提示：
 *
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 *
 * 进阶：
 * 如果不得使用临时缓冲区，该怎么解决？
 */
public class RemoveDuplicateNodes {

    public ListNode removeDuplicateNodes(ListNode head) {
        //用于记录首次出现的值
        Set<Integer> marks = new HashSet<>();
        ListNode pre = head;
        if (pre == null || pre.next == null) {
            return head;
        }
        ListNode cur = head.next;
        marks.add(pre.val);
        while (cur != null) {
            if (marks.contains(cur.val)) {
                //删除当前cur节点
                cur = cur.next;
                pre.next = cur;
            } else {
                marks.add(cur.val);
                pre = pre.next;
                cur = cur.next;
            }
        }
        return head;
    }
}
