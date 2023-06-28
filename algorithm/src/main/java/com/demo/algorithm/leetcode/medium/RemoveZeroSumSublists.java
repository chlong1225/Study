package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chl on 2023/6/11.
 * description : 从链表中删去总和值为零的连续节点
 *
 * 给你一个链表的头节点head，请你编写代码，反复删去链表中由总和值为0的连续节点组成的序列，直到不存在这样的序列为止。
 * 删除完毕后，请你返回最终结果链表的头节点。
 * 你可以返回任何满足题目要求的答案。
 * （注意，下面示例中的所有序列，都是对ListNode对象序列化的表示。）
 *
 * 示例 1：
 * 输入：head = [1,2,-3,3,1]
 * 输出：[3,1]
 * 提示：答案 [1,2,1] 也是正确的。
 *
 * 示例 2：
 * 输入：head = [1,2,3,-3,4]
 * 输出：[1,2,4]
 *
 * 示例 3：
 * 输入：head = [1,2,3,-3,-2]
 * 输出：[1]
 *
 * 提示：
 * 给你的链表中可能有1到1000个节点。
 * 对于链表中的每个节点，节点的值：-1000 <= node.val <= 1000.
 */
public class RemoveZeroSumSublists {

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode root = new ListNode();
        root.next = head;
        //记录前一个节点，求和
        Map<Integer, ListNode> marks = new HashMap<>();
        int sum = 0;
        ListNode p = head;
        while (p != null) {
            sum += p.val;
            marks.put(sum, p);
            p = p.next;
        }
        if (sum == 0) {
            return null;
        }
        sum = 0;
        p = root;
        while (p != null) {
            sum += p.val;
            p.next = marks.get(sum).next;
            p = p.next;
        }
        return root.next;
    }
}
