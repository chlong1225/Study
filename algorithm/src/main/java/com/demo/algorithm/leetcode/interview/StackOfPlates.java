package com.demo.algorithm.leetcode.interview;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by chl on 2022/6/3.
 * description : 面试题03.03. 堆盘子
 *
 * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。
 * 请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。
 * 此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。
 * 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt应返回 -1.
 *
 * 示例1:
 *  输入：
 * ["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
 * [[1], [1], [2], [1], [], []]
 *  输出：
 * [null, null, null, 2, 1, -1]
 *
 * 示例2:
 *  输入：
 * ["StackOfPlates", "push", "push", "push", "popAt", "popAt", "popAt"]
 * [[2], [1], [2], [3], [0], [0], [0]]
 *  输出：
 * [null, null, null, null, 2, 1, 3]
 */
public class StackOfPlates {

    private List<Deque<Integer>> dates = new ArrayList<>();
    private int max;

    public StackOfPlates(int cap) {
        dates.clear();
        max = cap;
    }

    public void push(int val) {
        if (max == 0) {
            return;
        }
        if (dates.size() == 0) {
            Deque<Integer> stack = new ArrayDeque<Integer>();
            stack.addLast(val);
            dates.add(stack);
        } else {
            Deque<Integer> last = this.dates.get(this.dates.size() - 1);
            if (last.size() == max) {
                //当前栈满了需要新建一个栈
                Deque<Integer> stack = new ArrayDeque<Integer>();
                stack.addLast(val);
                dates.add(stack);
            } else {
                last.addLast(val);
            }
        }
    }

    public int pop() {
        if (dates.size() == 0) {
            return -1;
        }
        Deque<Integer> last = dates.get(dates.size() - 1);
        int num = last.pollLast();
        if (last.isEmpty()) {
            dates.remove(dates.size() - 1);
        }
        return num;
    }

    public int popAt(int index) {
        if (dates.size() <= index) {
            return -1;
        }
        Deque<Integer> cur = dates.get(index);
        int num = cur.pollLast();
        if (cur.isEmpty()) {
            dates.remove(index);
        }
        return num;
    }
}
