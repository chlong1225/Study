package com.demo.algorithm.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * create on 2022/8/31
 * @author chenglong
 * description : 验证栈序列
 *
 * 给定pushed和popped两个序列，每个序列中的值都不重复，只有当它们可能是在最初空栈上进行的推入push和弹出pop操作序列的结果时返回true；否则返回false。
 *
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *
 * 提示：
 * 1 <= pushed.length <= 1000
 * 0 <= pushed[i] <= 1000
 * pushed 的所有元素互不相同
 * popped.length == pushed.length
 * popped是pushed的一个排列
 */
public class ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int length = pushed.length;
        stack.addLast(pushed[0]);
        int index = 0;
        for (int i = 1; i < length; i++) {
            while (!stack.isEmpty()) {
                if (stack.peekLast() == popped[index]) {
                    stack.pollLast();
                    index++;
                } else {
                    break;
                }
            }
            stack.addLast(pushed[i]);
        }
        while (!stack.isEmpty()) {
            if (stack.pollLast() != popped[index]) {
                return false;
            } else {
                index++;
            }
        }
        return true;
    }
}
