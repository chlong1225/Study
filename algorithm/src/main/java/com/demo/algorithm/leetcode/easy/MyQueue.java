package com.demo.algorithm.leetcode.easy;


import java.util.Stack;

/**
 * create by chenglong on 9/17/21
 * description : 用栈实现队列
 *
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *
 * 实现 MyQueue 类：
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 *  
 * 说明：
 * 你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 *  
 * 进阶：
 *
 * 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
 */
public class MyQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    private int size;

    public MyQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
        size = 0;
    }

    public void push(int x) {
        size++;
        if (stack1.isEmpty()) {
            stack2.push(x);
        } else {
            stack1.push(x);
        }
    }

    public int pop() {
        int result = 0;
        int count = size - 1;
        if (stack1.isEmpty()) {
            while (!stack2.isEmpty()) {
                result = stack2.pop();
                if (count > 0) {
                    count--;
                    stack1.push(result);
                }
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        } else {
            while (!stack1.isEmpty()) {
                result = stack1.pop();
                if (count > 0) {
                    count--;
                    stack2.push(result);
                }
            }
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        if (size > 0) {
            size--;
        }
        return result;
    }

    public int peek() {
        int result = 0;
        if (stack1.isEmpty()) {
            while (!stack2.isEmpty()) {
                result = stack2.pop();
                stack1.push(result);
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        } else {
            while (!stack1.isEmpty()) {
                result = stack1.pop();
                stack2.push(result);
            }
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        return result;
    }

    public boolean empty() {
        return size == 0;
    }
}
