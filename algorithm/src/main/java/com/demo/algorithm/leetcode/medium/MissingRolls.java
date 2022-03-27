package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/3/27.
 * description : 找出缺失的观测数据
 *
 * 现有一份n+m次投掷单个六面骰子的观测数据，骰子的每个面从1到6编号。观测数据中缺失了n份，你手上只拿到剩余m次投掷的数据。幸好你有之前计算过的这n+m次投掷数据的平均值 。
 * 给你一个长度为m的整数数组rolls ，其中rolls[i]是第i次观测的值。同时给你两个整数mean和n 。
 * 返回一个长度为n的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的平均值是 mean 。
 * 如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
 * k个数字的平均值为这些数字求和后再除以k 。
 * 注意 mean是一个整数，所以n + m次投掷的总和需要被n + m整除。
 *
 * 示例 1：
 * 输入：rolls = [3,2,4,3], mean = 4, n = 2
 * 输出：[6,6]
 * 解释：所有 n + m 次投掷的平均值是 (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4 。
 *
 * 示例 2：
 * 输入：rolls = [1,5,6], mean = 3, n = 4
 * 输出：[2,3,2,2]
 * 解释：所有 n + m 次投掷的平均值是 (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3 。
 *
 * 示例 3：
 * 输入：rolls = [1,2,3,4], mean = 6, n = 4
 * 输出：[]
 * 解释：无论丢失的 4 次数据是什么，平均值都不可能是 6 。
 *
 * 示例 4：
 * 输入：rolls = [1], mean = 3, n = 1
 * 输出：[5]
 * 解释：所有 n + m 次投掷的平均值是 (1 + 5) / 2 = 3 。
 *
 * 提示：
 * m == rolls.length
 * 1 <= n, m <= 10^5
 * 1 <= rolls[i], mean <= 6
 */
public class MissingRolls {

    public int[] missingRolls(int[] rolls, int mean, int n) {
        //1，统计剩余m次骰子的总和
        int sum = 0;
        int m = rolls.length;
        for (int i = 0; i < m; i++) {
            sum += rolls[i];
        }
        //2，计算缺失的n次总和
        int counts = (m + n) * mean - sum;
        //3，缺失的n次记录比较最大最小值
        int max = 6 * n;
        int min = n;
        if (counts > max || counts < min) {
            return new int[0];
        }
        int extra = counts - n;
        //3，以贪心的方式给n次进行赋值
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (extra == 0) {
                result[i] = 1;
            } else {
                if (extra <= 5) {
                    result[i] = (extra + 1);
                    extra = 0;
                } else {
                    result[i] = 6;
                    extra -= 5;
                }
            }
        }
        return result;
    }
}
