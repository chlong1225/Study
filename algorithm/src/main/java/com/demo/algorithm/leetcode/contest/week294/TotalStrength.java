package com.demo.algorithm.leetcode.contest.week294;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by chl on 2022/5/22.
 * description : 巫师的总力量和
 *
 * 作为国王的统治者，你有一支巫师军队听你指挥。
 * 给你一个下标从0开始的整数数组strength，其中strength[i]表示第i位巫师的力量值。
 * 对于连续的一组巫师（也就是这些巫师的力量值是strength的子数组），总力量定义为以下两个值的乘积：
 * 巫师中 最弱的能力值。
 * 组中所有巫师的个人力量值之和。
 * 请你返回所有巫师组的总力量之和。由于答案可能很大，请将答案对10^9 + 7取余后返回。
 * 子数组是一个数组里非空连续子序列。
 *
 * 示例 1：
 * 输入：strength = [1,3,1,2]
 * 输出：44
 * 解释：以下是所有连续巫师组：
 * - [1,3,1,2] 中 [1] ，总力量值为 min([1]) * sum([1]) = 1 * 1 = 1
 * - [1,3,1,2] 中 [3] ，总力量值为 min([3]) * sum([3]) = 3 * 3 = 9
 * - [1,3,1,2] 中 [1] ，总力量值为 min([1]) * sum([1]) = 1 * 1 = 1
 * - [1,3,1,2] 中 [2] ，总力量值为 min([2]) * sum([2]) = 2 * 2 = 4
 * - [1,3,1,2] 中 [1,3] ，总力量值为 min([1,3]) * sum([1,3]) = 1 * 4 = 4
 * - [1,3,1,2] 中 [3,1] ，总力量值为 min([3,1]) * sum([3,1]) = 1 * 4 = 4
 * - [1,3,1,2] 中 [1,2] ，总力量值为 min([1,2]) * sum([1,2]) = 1 * 3 = 3
 * - [1,3,1,2] 中 [1,3,1] ，总力量值为 min([1,3,1]) * sum([1,3,1]) = 1 * 5 = 5
 * - [1,3,1,2] 中 [3,1,2] ，总力量值为 min([3,1,2]) * sum([3,1,2]) = 1 * 6 = 6
 * - [1,3,1,2] 中 [1,3,1,2] ，总力量值为 min([1,3,1,2]) * sum([1,3,1,2]) = 1 * 7 = 7
 * 所有力量值之和为 1 + 9 + 1 + 4 + 4 + 4 + 3 + 5 + 6 + 7 = 44 。
 *
 * 示例 2：
 * 输入：strength = [5,4,6]
 * 输出：213
 * 解释：以下是所有连续巫师组：
 * - [5,4,6] 中 [5] ，总力量值为 min([5]) * sum([5]) = 5 * 5 = 25
 * - [5,4,6] 中 [4] ，总力量值为 min([4]) * sum([4]) = 4 * 4 = 16
 * - [5,4,6] 中 [6] ，总力量值为 min([6]) * sum([6]) = 6 * 6 = 36
 * - [5,4,6] 中 [5,4] ，总力量值为 min([5,4]) * sum([5,4]) = 4 * 9 = 36
 * - [5,4,6] 中 [4,6] ，总力量值为 min([4,6]) * sum([4,6]) = 4 * 10 = 40
 * - [5,4,6] 中 [5,4,6] ，总力量值为 min([5,4,6]) * sum([5,4,6]) = 4 * 15 = 60
 * 所有力量值之和为 25 + 16 + 36 + 36 + 40 + 60 = 213 。
 *
 * 提示：
 * 1 <= strength.length <= 10^5
 * 1 <= strength[i] <= 10^9
 */
public class TotalStrength {

    private static final int MOD = 1000000007;

    public int totalStrength(int[] strength) {
        int length = strength.length;
        long base = 1;
        if (length == 1) {
            long total = base * strength[0] * strength[0];
            return (int) (total % MOD);
        }
        //1，统计左边比当前值大的最小位置
        int[] left = new int[length];
        left[0] = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.add(0);
        for (int i = 1; i < length; i++) {
            int cur = strength[i];
            while (stack.size() > 0 && strength[stack.peekLast()] > cur) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                left[i] = 0;
            } else {
                left[i] = stack.peekLast() + 1;
            }
            stack.add(i);
        }
        //2，统计右边比当前值大的最大位置
        int[] right = new int[length];
        right[length - 1] = length - 1;
        stack.clear();
        stack.add(length - 1);
        for (int i = length - 2; i >= 0; i--) {
            int cur = strength[i];
            while (stack.size() > 0 && strength[stack.peekLast()] >= cur) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                right[i] = length - 1;
            } else {
                right[i] = stack.peekLast() - 1;
            }
            stack.add(i);
        }
        //3，计算前缀和
        int s = 0;
        long[] sums = new long[length + 2];
        for (int i = 1; i <= length; i++) {
            s += strength[i - 1];
            sums[i + 1] = (sums[i] + s) % MOD;
        }
        long total = 0;
        for (int i = 0; i < length; i++) {
            long count = base * (i - left[i] + 1) * (sums[right[i] + 2] - sums[i + 1]) - base * (right[i] - i + 1) * (sums[i + 1] - sums[left[i]]);
            count %= MOD;
            total += (count * strength[i]);
            total %= MOD;
        }
        return (int) (total + MOD) % MOD;
    }

}
