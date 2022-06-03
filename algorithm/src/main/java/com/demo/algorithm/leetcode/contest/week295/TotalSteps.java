package com.demo.algorithm.leetcode.contest.week295;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by chl on 2022/6/3.
 * description : 使数组按非递减顺序排列
 *
 * 给你一个下标从0开始的整数数组nums。在一步操作中，移除所有满足nums[i - 1]>nums[i]的 nums[i]，其中 0 < i < nums.length。
 * 重复执行步骤，直到nums 变为非递减数组，返回所需执行的操作数。
 *
 * 示例 1：
 * 输入：nums = [5,3,4,4,7,3,6,11,8,5,11]
 * 输出：3
 * 解释：执行下述几个步骤：
 * - 步骤 1 ：[5,3,4,4,7,3,6,11,8,5,11] 变为 [5,4,4,7,6,11,11]
 * - 步骤 2 ：[5,4,4,7,6,11,11] 变为 [5,4,7,11,11]
 * - 步骤 3 ：[5,4,7,11,11] 变为 [5,7,11,11]
 * [5,7,11,11] 是一个非递减数组，因此，返回 3 。
 *
 * 示例 2：
 * 输入：nums = [4,5,7,7,13]
 * 输出：0
 * 解释：nums 已经是一个非递减数组，因此，返回 0 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class TotalSteps {

    public int totalSteps(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        int max = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            int cur = nums[i];
            int step = 0;
            while (stack.size() > 0 && stack.peekLast()[0] <= cur) {
                step = Math.max(step, stack.pollLast()[1]);
            }
            if (stack.isEmpty()) {
                step = 0;
            } else {
                step++;
            }
            if (step > max) {
                max = step;
            }
            stack.add(new int[]{cur, step});
        }
        return max;
    }
}
