package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * create on 2023/12/25
 * @author chenglong
 * description : 对链表进行插入排序
 *
 * 给定单个链表的头head ，使用插入排序对链表进行排序，并返回排序后链表的头 。
 * 插入排序算法的步骤:
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
 * 对链表进行插入排序。
 *
 * 示例 1：
 * 输入: head = [4,2,1,3]
 * 输出: [1,2,3,4]
 *
 * 示例 2：
 * 输入: head = [-1,5,3,4,0]
 * 输出: [-1,0,3,4,5]
 *
 * 提示：
 * 列表中的节点数在 [1, 5000]范围内
 * -5000 <= Node.val <= 5000
 */
public class InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        ListNode first = head;
        //被插入的节点
        ListNode insert = head.next;
        first.next = null;
        while (insert != null) {
            ListNode nextInsert = insert.next;
            if (insert.val <= first.val) {
                //此时头节点改为insert节点
                insert.next = first;
                first = insert;
            } else {
                ListNode p = first;
                ListNode q = first.next;
                boolean hasFind = false;
                while (q != null) {
                    //此时p<insert
                    if (insert.val <= q.val) {
                        p.next = insert;
                        insert.next = q;
                        hasFind = true;
                        break;
                    } else {
                        p = p.next;
                        q = q.next;
                    }
                }
                if (!hasFind) {
                    p.next = insert;
                    insert.next = null;
                }
            }
            insert = nextInsert;
        }
        return first;
    }
}
