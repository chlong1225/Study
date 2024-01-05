package com.demo.algorithm.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 2024/1/5
 * @author chenglong
 * description : 队列中可以看到的人数
 *
 * 有n个人排成一个队列，从左到右编号为0到n-1。给你以一个整数数组heights，每个整数互不相同，heights[i]表示第i个人的高度。
 * 一个人能看到他右边另一个人的条件是这两人之间的所有人都比他们两人矮。更正式的，第i个人能看到第j个人的条件是i < j 且
 * min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]) 。
 * 请你返回一个长度为n的数组answer，其中answer[i]是第i个人在他右侧队列中能看到的人数。
 *
 * 示例 1：
 * 输入：heights = [10,6,8,5,11,9]
 * 输出：[3,1,2,1,1,0]
 * 解释：
 * 第 0 个人能看到编号为 1 ，2 和 4 的人。
 * 第 1 个人能看到编号为 2 的人。
 * 第 2 个人能看到编号为 3 和 4 的人。
 * 第 3 个人能看到编号为 4 的人。
 * 第 4 个人能看到编号为 5 的人。
 * 第 5 个人谁也看不到因为他右边没人。
 *
 * 示例 2：
 * 输入：heights = [5,1,2,3,10]
 * 输出：[4,1,1,1,0]
 *
 * 提示：
 * n == heights.length
 * 1 <= n <= 10^5
 * 1 <= heights[i] <= 10^5
 * heights 中所有数互不相同 。
 */
public class SeePersonsCount {

    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] answers = new int[n];
        answers[n - 1] = 0;
        if (n == 1) {
            return answers;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(heights[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            int cur = heights[i];
            while (stack.size() > 0) {
                if (stack.peekLast() < cur) {
                    answers[i]++;
                    stack.pollLast();
                } else {
                    break;
                }
            }
            if (stack.size() > 0) {
                answers[i]++;
            }
            stack.addLast(cur);
        }
        return answers;
    }
}
