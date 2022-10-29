package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * create on 2022/10/12
 * @author chenglong
 * description : 链表组件
 *
 * 给定链表头结点head，该链表上的每个结点都有一个唯一的整型值。同时给定列表nums，该列表是上述链表中整型值的一个子集。
 * 返回列表nums中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表nums中）构成的集合。
 *
 * 示例1：
 * 输入: head = [0,1,2,3], nums = [0,1,3]
 * 输出: 2
 * 解释: 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。
 *
 * 示例 2：
 * 输入: head = [0,1,2,3,4], nums = [0,3,1,4]
 * 输出: 2
 * 解释: 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 *
 * 提示：
 * 链表中节点数为n
 * 1 <= n <= 10^4
 * 0 <= Node.val < n
 * Node.val中所有值不同
 * 1 <= nums.length <= n
 * 0 <= nums[i] < n
 * nums 中所有值 不同
 */
public class NumComponents {

    public int numComponents(ListNode head, int[] nums) {
        boolean[] marks = new boolean[10000];
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            marks[nums[i]] = true;
        }
        int count = 0;
        int num = 0;
        while (head != null) {
            int cur = head.val;
            if (marks[cur]) {
                num++;
            } else {
                if (num > 0) {
                    count++;
                    num = 0;
                }
            }
            head = head.next;
        }
        if (num > 0) {
            count++;
        }
        return count;
    }
}
