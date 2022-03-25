package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/3/25.
 * description : 阶乘后的零
 *
 * 给定一个整数n ，返回n!结果中尾随零的数量。
 * 提示n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 *
 * 示例 3：
 * 输入：n = 0
 * 输出：0
 *
 * 提示：
 * 0 <= n <= 10^4
 *
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 */
public class TrailingZeroes {

    public int trailingZeroes(int n) {
        if (n < 5) {
            return 0;
        }
        int sum = 0;
        while (n >= 5) {
            sum += (n / 5);
            n /= 5;
        }
        return sum;
    }
}
