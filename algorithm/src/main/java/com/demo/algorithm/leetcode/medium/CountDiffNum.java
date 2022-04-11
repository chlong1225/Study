package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/4/11.
 * description : 统计各位数字都不同的数字个数
 *
 * 给你一个整数n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：1
 *
 * 提示：
 * 0 <= n <= 8
 */
public class CountDiffNum {

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int sum = 1;
        int cur;
        int curCount = 1;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                cur = 9;
            } else {
                cur = 11 - i;
            }
            curCount *= cur;
            sum += curCount;
        }
        return sum;
    }
}
