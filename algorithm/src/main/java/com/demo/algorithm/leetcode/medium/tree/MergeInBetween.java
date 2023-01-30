package com.demo.algorithm.leetcode.medium.tree;

/**
 * create on 2023/1/30
 * @author chenglong
 * description : 合并两个链表
 *
 * 给你两个链表list1和list2，它们包含的元素分别为n个和m个。
 * 请你将list1中下标从a到b的全部节点都删除，并将list2接在被删除节点的位置。
 * 请你返回结果链表的头指针。
 *
 * 示例 1：
 * 输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * 输出：[0,1,2,1000000,1000001,1000002,5]
 * 解释：我们删除 list1 中下标为 3 和 4 的两个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
 *
 * 示例 2：
 * 输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
 * 输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
 * 解释：上图中蓝色的边和节点为答案链表。
 *
 * 提示：
 * 3 <= list1.length <= 10^4
 * 1 <= a <= b < list1.length - 1
 * 1 <= list2.length <= 10^4
 */
public class MergeInBetween {

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        //删除的起始点
        ListNode pre = list1;
        ListNode p = list1.next;
        int index = 1;
        while (index < a) {
            pre = p;
            p = p.next;
            index++;
        }
        if (a == b) {
            //删除p节点
            if (p.next == null) {
                //p节点为最后一个元素，此时list2拼接到p的位置
                pre.next = list2;
            } else {
                //p节点为中间位置的节点，需要将list2插入到p的位置
                //获取list2的最后节点
                ListNode last = list2;
                while (last.next != null) {
                    last = last.next;
                }
                pre.next = list2;
                last.next = p.next;
            }
        } else {
            //获取删除的截止节点q
            ListNode q = p;
            while (index < b) {
                q = q.next;
                index++;
            }
            if (q.next == null) {
                pre.next = list2;
            } else {
                ListNode last = list2;
                while (last.next != null) {
                    last = last.next;
                }
                pre.next = list2;
                last.next = q.next;
            }
        }
        return list1;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
