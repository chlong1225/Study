package com.demo.algorithm.leetcode.offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by chl on 2022/4/20.
 * description : 剑指Offer59-I. 滑动窗口的最大值
 *
 * 给定一个数组nums和滑动窗口的大小k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
public class MaxSlidingWindow {

    private Deque<Integer> marks = new ArrayDeque<Integer>();

    public int[] maxSlidingWindow(int[] nums, int k) {
        marks.clear();
        if (nums == null) {
            return new int[0];
        }
        int length = nums.length;
        if (length == 0) {
            return new int[0];
        }
        int n = length - k + 1;
        int[] result = new int[n];
        for (int i = 0; i < k; i++) {
            while (!marks.isEmpty() && marks.peekLast() < nums[i]) {
                marks.pollLast();
            }
            marks.addLast(nums[i]);
        }
        result[0] = marks.peekFirst();
        for (int i = k; i < length; i++) {
            //滑动窗口移除前一位
            if (nums[i - k] == marks.peekFirst()) {
                marks.pollFirst();
            }
            //添加后一位
            while (!marks.isEmpty() && marks.peekLast() < nums[i]) {
                marks.pollLast();
            }
            marks.addLast(nums[i]);
            result[i - k + 1] = marks.peekFirst();
        }
        return result;
    }
}
