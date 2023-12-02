package com.demo.algorithm.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 2023/11/28
 * @author chenglong
 * description : 设计前中后队列
 *
 * 请你设计一个队列，支持在前，中，后三个位置的push和pop操作。
 * 请你完成 FrontMiddleBack 类：
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的正中间 。
 * void pushBack(int val) 将 val 添加到队里的最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有两个中间位置的时候，选择靠前面的位置进行操作。比方说：
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 *
 * 示例 1：
 * 输入：
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * 输出：
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 * 解释：
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // 返回 1 -> [4, 3, 2]
 * q.popMiddle();    // 返回 3 -> [4, 2]
 * q.popMiddle();    // 返回 4 -> [2]
 * q.popBack();      // 返回 2 -> []
 * q.popFront();     // 返回 -1 -> [] （队列为空）
 *
 * 提示：
 * 1 <= val <= 10^9
 * 最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack 。
 */
public class FrontMiddleBackQueue {

    private final Deque<Integer> left = new ArrayDeque<>();
    private final Deque<Integer> right = new ArrayDeque<>();

    public FrontMiddleBackQueue() {
        left.clear();
        right.clear();
    }

    public void pushFront(int val) {
        left.addFirst(val);
        if (left.size() == right.size() + 2) {
            right.addFirst(left.pollLast());
        }
    }

    public void pushMiddle(int val) {
        if (left.size() == right.size()) {
            left.addLast(val);
        } else {
            right.addFirst(left.pollLast());
            left.addLast(val);
        }
    }

    public void pushBack(int val) {
        right.addLast(val);
        if (right.size() > left.size()) {
            left.addLast(right.pollFirst());
        }
    }

    public int popFront() {
        if (left.isEmpty()) {
            return -1;
        }
        int value = left.pollFirst();
        if (left.size() < right.size()) {
            left.addLast(right.pollFirst());
        }
        return value;
    }

    public int popMiddle() {
        if (left.isEmpty()) {
            return -1;
        }
        int value = left.pollLast();
        if (left.size() < right.size()) {
            left.addLast(right.pollFirst());
        }
        return value;
    }

    public int popBack() {
        if (right.isEmpty()) {
            if (left.isEmpty()) {
                return -1;
            } else {
                return left.pollLast();
            }
        }
        int value = right.pollLast();
        if (left.size() == right.size() + 2) {
            right.addFirst(left.pollLast());
        }
        return value;
    }
}
