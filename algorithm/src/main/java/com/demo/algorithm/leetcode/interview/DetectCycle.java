package com.demo.algorithm.leetcode.interview;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2022/5/22.
 * description : 面试题02.08. 环路检测
 *
 * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。若环不存在，请返回null。
 * 如果链表中有某个节点，可以通过连续跟踪next指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数pos来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果pos是-1，则在该链表中没有环。注意：pos不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 *
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        //存在环时，next肯定不为null
        ListNode fast = head;
        ListNode slow = head;
        boolean hasRing = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasRing = true;
                break;
            }
        }
        if (hasRing) {
            ListNode pre = head;
            while (pre != slow) {
                pre = pre.next;
                slow = slow.next;
            }
            return pre;
        }
        return null;
    }
}
