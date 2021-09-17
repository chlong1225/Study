package com.demo.algorithm.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * create by chenglong on 9/16/21
 * description : 用队列实现栈
 * <p>
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 实现 MyStack 类：
 * <p>
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *  
 * 注意：
 * 你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *  
 * 示例：
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 * <p>
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 *  
 * 提示：
 * <p>
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 */
public class MyStack {

    private LinkedList<Integer> list1;
    private LinkedList<Integer> list2;
    private int size = 0;

    public MyStack() {
        list1 = new LinkedList();
        list2 = new LinkedList();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        size++;
        if (list1.isEmpty()) {
            list2.offer(x);
        } else {
            list1.offer(x);
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        int result = 0;
        //最多添加的元素
        int max = size - 1;
        if (list1.isEmpty()) {
            //元素在list2中
            while (!list2.isEmpty()) {
                result = list2.pop();
                if (max > 0) {
                    max--;
                    list1.offer(result);
                }
            }
        } else {
            while (!list1.isEmpty()) {
                result = list1.pop();
                if (max > 0) {
                    max--;
                    list2.offer(result);
                }
            }
        }
        size--;
        return result;
    }

    /**
     * Get the top element.
     */
    public int top() {
        int result = 0;
        if (list1.isEmpty()) {
            //元素在list2中
            while (!list2.isEmpty()) {
                result = list2.pop();
                list1.offer(result);
            }
        } else {
            while (!list1.isEmpty()) {
                result = list1.pop();
                list2.offer(result);
            }
        }
        return result;
    }

    public boolean empty() {
        return size == 0;
    }
}
