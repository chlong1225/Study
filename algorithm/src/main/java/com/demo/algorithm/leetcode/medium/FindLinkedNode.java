package com.demo.algorithm.leetcode.medium;

import com.demo.algorithm.leetcode.entity.ListNode;

import java.util.Random;

/**
 * create on 2022/1/18
 * @author chenglong
 * description : 链表随机节点
 *
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点被选中的概率一样 。
 * 实现FindLinkedNode类：
 * FindLinkedNode(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 *
 * 示例：
 * 输入
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * 输出
 * [null, 1, 3, 2, 2, 3]
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // 返回 1
 * solution.getRandom(); // 返回 3
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 2
 * solution.getRandom(); // 返回 3
 * // getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。
 *
 * 提示：
 * 链表中的节点数在范围 [1, 10^4] 内
 * -10^4 <= Node.val <= 10^4
 * 至多调用getRandom 方法 10^4 次
 *
 * 进阶：
 * 如果链表非常大且长度未知，该怎么处理？
 * 你能否在不使用额外空间的情况下解决此问题？
 */
public class FindLinkedNode {

    private int count;
    private int[] datas;
    private Random mRandom;

    public FindLinkedNode(ListNode head) {
        //防止list多次扩容，先获取链表的数量
        count = 0;
        mRandom = new Random();
        ListNode p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        datas = new int[count];
        p = head;
        int index = 0;
        while (p != null) {
            datas[index] = p.val;
            p = p.next;
            index++;
        }
    }

    public int getRandom() {
        int index = mRandom.nextInt(count);
        return datas[index];
    }
}
