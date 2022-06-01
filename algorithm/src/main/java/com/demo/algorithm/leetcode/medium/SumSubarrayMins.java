package com.demo.algorithm.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by chl on 2022/5/29.
 * description : 子数组的最小值之和
 *
 * 给定一个整数数组arr，找到min(b)的总和，其中b的范围为arr的每个（连续）子数组。
 * 由于答案可能很大，因此返回答案模10^9 + 7 。
 *
 * 示例 1：
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 *
 * 示例 2：
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 *
 * 提示：
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 3 * 10^4
 */
public class SumSubarrayMins {

    private static final int MOD = 1000000007;

    public int sumSubarrayMins(int[] arr) {
        int length = arr.length;
        if (length == 1) {
            return arr[0];
        }
        //用于记录左边大于当前值最小的index
        int[] left = new int[length];
        left[0] = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.add(0);
        for (int i = 1; i < length; i++) {
            int cur = arr[i];
            while (stack.size() > 0 && arr[stack.peekLast()] >= cur) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                left[i] = 0;
            } else {
                left[i] = stack.peekLast() + 1;
            }
            stack.add(i);
        }
        //用于记录右边比当前值大的最大index
        int[] right = new int[length];
        right[length - 1] = length - 1;
        stack.clear();
        stack.add(length - 1);
        for (int i = length - 2; i >= 0; i--) {
            int cur = arr[i];
            while (stack.size() > 0 && arr[stack.peekLast()] > cur) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                right[i] = length - 1;
            } else {
                right[i] = stack.peekLast() - 1;
            }
            stack.add(i);
        }
        int sum = 0;
        long base = 1;
        for (int i = 0; i < length; i++) {
            long count = base * (i - left[i] + 1) * (right[i] - i + 1);
            long total = count * arr[i] % MOD;
            sum += total;
            sum %= MOD;
        }
        return sum;
    }
}
