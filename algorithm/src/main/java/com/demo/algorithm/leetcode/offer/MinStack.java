package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/25.
 * description : 包含min函数的栈
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数在该栈中，调用min、push及pop的时间复杂度都是O(1)。
 *
 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *
 * 提示：
 * 各函数的调用总次数不超过 20000 次
 */
public class MinStack {

    private MinStackNode head ;

    public MinStack() {
    }

    public void push(int x) {
        MinStackNode node = new MinStackNode();
        node.val = x;
        if (head != null) {
            node.min = Math.min(x, head.min);
            node.next = head;
            head = node;
        } else {
            node.min = x;
            head = node;
        }
    }

    public void pop() {
        if (head != null) {
            head = head.next;
        }
    }

    public int top() {
        if (head == null) {
            return 0;
        }
        return head.val;
    }

    public int min() {
        if (head == null) {
            return 0;
        }
        return head.min;
    }

    public static class MinStackNode{

        //当前节点的值
        private int val;
        //当前节点以及之前节点的最小值
        private int min;
        //下一个节点
        private MinStackNode next;

    }
}
