package com.demo.algorithm.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/12/22
 *
 * @author chenglong
 * description : N次操作后的最大分数和
 * <p>
 * 给你nums，它是一个大小为2*n的正整数数组。你必须对这个数组执行n次操作。
 * 在第i次操作时（操作编号从1开始），你需要：
 * 选择两个元素x和y。
 * 获得分数i * gcd(x, y)。
 * 将x和y从nums中删除。
 * 请你返回n次操作后你能获得的分数和最大为多少。
 * 函数gcd(x, y)是x和y的最大公约数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2]
 * 输出：1
 * 解释：最优操作是：
 * (1 * gcd(1, 2)) = 1
 * <p>
 * 示例 2：
 * 输入：nums = [3,4,6,8]
 * 输出：11
 * 解释：最优操作是：
 * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,3,4,5,6]
 * 输出：14
 * 解释：最优操作是：
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 * <p>
 * 提示：
 * 1 <= n <= 7
 * nums.length == 2 * n
 * 1 <= nums[i] <= 10^6
 */
public class MaxScore {

    private int max = 0;
    private Map<Integer, Integer> crash = new HashMap<>();

    public int maxScore(int[] nums) {
        //1, 预处理最大公约数
        int n = nums.length;
        int[][] dates = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //分别对应nums[i],nums[j]
                if (i == j) {
                    dates[i][j] = nums[i];
                } else {
                    if (dates[i][j] == 0 && dates[j][i] == 0) {
                        int gcd = gcd(nums[i], nums[j]);
                        dates[i][j] = gcd;
                        dates[j][i] = gcd;
                    }
                }
            }
        }
        //2，使用回溯算法
        max = 0;
        return max;
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
