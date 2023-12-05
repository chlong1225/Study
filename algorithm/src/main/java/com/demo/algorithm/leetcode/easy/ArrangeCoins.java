package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/5
 * @author chenglong
 * description : 排列硬币
 *
 * 你总共有n枚硬币，并计划将它们按阶梯状排列。对于一个由k行组成的阶梯，其第i行必须正好有i枚硬币。阶梯的最后一行可能是不完整的。
 * 给你一个数字n，计算并返回可形成完整阶梯行的总行数。
 *
 * 示例 1：
 * 输入：n = 5
 * 输出：2
 * 解释：因为第三行不完整，所以返回2。
 *
 * 示例 2：
 * 输入：n = 8
 * 输出：3
 * 解释：因为第四行不完整，所以返回3。
 *
 * 提示：
 * 1 <= n <= 2^31 - 1
 */
public class ArrangeCoins {

    public int arrangeCoins(int n) {
        if (n < 3) {
            return 1;
        }
        if (n < 6) {
            return 2;
        }
        long base = 1;
        int start = 1;
        int end = n;
        while (start < end) {
            int middle = (end - start) / 2 + start;
            long total = base * middle * (middle + 1) / 2;
            if (total == n) {
                return middle;
            } else if (total < n) {
                start = middle + 1;
                long tem = base * start * (start + 1) / 2;
                if (tem > n) {
                    return middle;
                }
            } else {
                end = middle - 1;
            }
        }
        return start;
    }
}
