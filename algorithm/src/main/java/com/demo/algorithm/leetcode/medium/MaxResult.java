package com.demo.algorithm.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 2024/2/5
 * @author chenglong
 * description : 跳跃游戏VI
 *
 * 给你一个下标从0开始的整数数组nums和一个整数k。
 * 一开始你在下标0处。每一步你最多可以往前跳k步，但你不能跳出数组的边界。也就是说，你可以从下标i跳到[i+1，min(n-1,i+k)] 包含两个端点的任意位置。
 * 你的目标是到达数组最后一个位置（下标为n-1），你的得分为经过的所有数字之和。
 * 请你返回你能得到的最大得分。
 *
 * 示例 1：
 * 输入：nums = [1,-1,-2,4,-7,3], k = 2
 * 输出：7
 * 解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
 *
 * 示例 2：
 * 输入：nums = [10,-5,-2,4,0,3], k = 3
 * 输出：17
 * 解释：你可以选择子序列 [10,4,3] （上面加粗数字），和为 17 。
 *
 * 示例 3：
 * 输入：nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * 输出：0
 *
 * 提示：
 *  1 <= nums.length, k <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class MaxResult {

    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] marks = new int[n];
        marks[0] = nums[0];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.addLast(0);
        for (int i = 1; i < n; i++) {
            while (stack.size() > 0) {
                int index = stack.peekFirst();
                if (i - index > k) {
                    stack.pollFirst();
                } else {
                    break;
                }
            }
            marks[i] = nums[i] + marks[stack.peekFirst()];
            while (stack.size() > 0) {
                if (marks[stack.peekLast()] <= marks[i]) {
                    stack.pollLast();
                } else {
                    break;
                }
            }
            stack.addLast(i);
        }
        return marks[n-1];
    }
}
