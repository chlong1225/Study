package com.demo.algorithm.leetcode.offer;

import com.demo.algorithm.leetcode.entity.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/3/25.
 * description : 剑指 Offer 06. 从尾到头打印链表
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 * 0 <= 链表长度 <= 10000
 */
public class ReversePrint {

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        List<Integer> dates = new ArrayList<>();
        while (head != null) {
            dates.add(head.val);
            head = head.next;
        }
        int[] result = new int[dates.size()];
        for (int i = 0; i < dates.size(); i++) {
            int index = dates.size() - 1 - i;
            result[i] = dates.get(index);
        }
        return result;
    }

    public int[] reversePrint2(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        int count = 0;
        ListNode cur = null;
        while (head != null) {
            count++;
            ListNode tem = cur;
            cur = head;
            head = head.next;
            cur.next = tem;
        }
        int[] result = new int[count];
        int index = 0;
        while (cur != null) {
            result[index] = cur.val;
            cur = cur.next;
            index++;
        }
        return result;
    }
}
