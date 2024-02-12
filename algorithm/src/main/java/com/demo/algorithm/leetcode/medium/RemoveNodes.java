package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 2024/1/3
 * @author chenglong
 * description : 从链表中移除节点
 *
 * 给你一个链表的头节点head。
 * 移除每个右侧有一个更大数值的节点。
 * 返回修改后链表的头节点head。
 *
 * 示例 1：
 * 输入：head = [5,2,13,3,8]
 * 输出：[13,8]
 * 解释：需要移除的节点是 5 ，2 和 3 。
 * - 节点 13 在节点 5 右侧。
 * - 节点 13 在节点 2 右侧。
 * - 节点 8 在节点 3 右侧。
 *
 * 示例 2：
 * 输入：head = [1,1,1,1]
 * 输出：[1,1,1,1]
 * 解释：每个节点的值都是 1 ，所以没有需要移除的节点。
 *
 * 提示：
 * 给定列表中的节点数目在范围 [1, 10^5] 内
 * 1 <= Node.val <= 10^5
 */
public class RemoveNodes {

    public ListNode removeNodes(ListNode head) {
        ListNode p = head;
        Deque<ListNode> stack = new ArrayDeque<>();
        while (p != null) {
            while (stack.size() > 0) {
                if (stack.peekLast().val < p.val) {
                    stack.pollLast();
                } else {
                    break;
                }
            }
            stack.addLast(p);
            p = p.next;
        }
        ListNode answer = stack.pollFirst();
        p = answer;
        while (stack.size() > 0) {
            p.next = stack.pollFirst();
            p = p.next;
        }
        return answer;
    }
}
