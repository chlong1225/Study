package com.demo.algorithm.leetcode.interview;

import com.demo.algorithm.leetcode.entity.ListNode;

/**
 * Created by chl on 2022/5/27.
 * description : 面试题02.05. 链表求和
 *
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 *
 * 示例：
 * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 * 输出：2 -> 1 -> 9，即912
 * 进阶：思考一下，假设这些数位是正向存放的，又该如何解决呢?
 *
 * 示例：
 * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
 * 输出：9 -> 1 -> 2，即912
 */
public class AddTwoNumbers {

   public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      if (l1 == null) {
         return l2;
      }
      if (l2 == null) {
         return l1;
      }
      boolean hasAdd = false;
      ListNode root = new ListNode();
      ListNode p = root;
      while (l1 != null && l2 != null) {
         int sum = l1.val + l2.val;
         if (hasAdd) {
            sum++;
            hasAdd = false;
         }
         if (sum >= 10) {
            sum -= 10;
            hasAdd = true;
         }
         p.next = new ListNode(sum);
         p = p.next;
         l1 = l1.next;
         l2 = l2.next;
      }
      if (l1 != null) {
         while (l1 != null) {
            int sum = l1.val + (hasAdd ? 1 : 0);
            hasAdd = false;
            if (sum >= 10) {
               sum -= 10;
               hasAdd = true;
            }
            p.next = new ListNode(sum);
            p = p.next;
            l1 = l1.next;
         }
      } else {
         while (l2 != null) {
            int sum = l2.val + (hasAdd ? 1 : 0);
            hasAdd = false;
            if (sum >= 10) {
               sum -= 10;
               hasAdd = true;
            }
            p.next = new ListNode(sum);
            p = p.next;
            l2 = l2.next;
         }
      }
      if (hasAdd) {
         p.next = new ListNode(1);
      }
      return root.next;
   }
}
