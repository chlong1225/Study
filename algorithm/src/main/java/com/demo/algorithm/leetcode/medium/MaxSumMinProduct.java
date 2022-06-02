package com.demo.algorithm.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by chl on 2022/6/2.
 * description : 子数组最小乘积的最大值
 *
 * 一个数组的最小乘积定义为这个数组中最小值乘以数组的和。
 * 比方说，数组[3,2,5]（最小值是2）的最小乘积为2*(3+2+5) = 2*10 = 20。
 * 给你一个正整数数组nums，请你返回nums任意非空子数组的最小乘积的最大值。由于答案可能很大，请你返回答案对10^9+7取余的结果。
 * 请注意，最小乘积的最大值考虑的是取余操作之前的结果。题目保证最小乘积的最大值在不取余的情况下可以用64位有符号整数保存。
 * 子数组定义为一个数组的连续部分。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,2]
 * 输出：14
 * 解释：最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
 * 2 * (2+3+2) = 2 * 7 = 14 。
 *
 * 示例 2：
 * 输入：nums = [2,3,3,1,2]
 * 输出：18
 * 解释：最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
 * 3 * (3+3) = 3 * 6 = 18 。
 *
 * 示例 3：
 * 输入：nums = [3,1,5,6,4,2]
 * 输出：60
 * 解释：最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
 * 4 * (5+6+4) = 4 * 15 = 60 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^7
 */
public class MaxSumMinProduct {

    private static final int MOD = 1000000007;

    public int maxSumMinProduct(int[] nums) {
        int length = nums.length;
        long base = 1;
        if (length == 1) {
            long result = base * nums[0] * nums[0];
            return (int) (result % MOD);
        }
        //1，计算数据的前缀和
        long[] sums = new long[length];
        sums[0] = nums[0];
        for (int i = 1; i < length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        //2，记录当前位置左边大于当前值的最小位置
        int[] left = new int[length];
        left[0] = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(0);
        for (int i = 1; i < length; i++) {
            int cur = nums[i];
            while (stack.size() > 0 && nums[stack.peekLast()] >= cur) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                left[i] = 0;
            } else {
                left[i] = stack.peekLast() + 1;
            }
            stack.add(i);
        }
        //3，记录当前位置右边大于当前值的最大位置
        int[] right = new int[length];
        right[length - 1] = length - 1;
        stack.clear();
        stack.add(length - 1);
        for (int i = length - 2; i >= 0; i--) {
            int cur = nums[i];
            while (stack.size() > 0 && nums[stack.peekLast()] > cur) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                right[i] = length - 1;
            } else {
                right[i] = stack.peekLast() - 1;
            }
            stack.add(i);
        }
        //4，依次计算最小乘积，并获取最大值
        long max = 0;
        for (int i = 0; i < length; i++) {
            int cur = nums[i];
            long sum = sums[right[i]];
            if (left[i] > 0) {
                sum -= sums[left[i] - 1];
            }
            sum *= cur;
            if (sum > max) {
                max = sum;
            }
        }
        return (int) (max % MOD);
    }
}
