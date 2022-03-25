package com.demo.algorithm.leetcode.offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by chl on 2022/3/25.
 * description : 剑指 Offer 09. 用两个栈实现队列
 *
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail和deleteHead，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1)
 *
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 *
 * 提示：
 * 1 <= values <= 10000
 * 最多会对appendTail、deleteHead 进行10000次调用
 */
public class CQueue {

    private int size;
    //用于存放数据
    private Deque<Integer> stack1 = new ArrayDeque<>();
    //用于临时翻转数据
    private Deque<Integer> stack2 = new ArrayDeque<>();

    public CQueue() {
        size = 0;
        stack1.clear();
        stack2.clear();
    }

    public void appendTail(int value) {
        stack1.addLast(value);
        size++;
    }

    public int deleteHead() {
        if (size == 0) {
            return -1;
        }
        while (!stack1.isEmpty()) {
            stack2.addLast(stack1.pollLast());
        }
        int last = stack2.pollLast();
        size--;
        while (!stack2.isEmpty()) {
            stack1.addLast(stack2.pollLast());
        }
        return last;
    }
}
