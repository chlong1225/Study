package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/4/21
 * @author chenglong
 * description : 最小偶倍数
 *
 * 给你一个正整数n，返回2和n的最小公倍数（正整数）。
 *
 * 示例 1：
 * 输入：n = 5
 * 输出：10
 * 解释：5 和 2 的最小公倍数是 10 。
 *
 * 示例 2：
 * 输入：n = 6
 * 输出：6
 * 解释：6 和 2 的最小公倍数是 6 。注意数字会是它自身的倍数。
 *
 * 提示：
 * 1 <= n <= 150
 */
public class SmallestEvenMultiple {

    public int smallestEvenMultiple(int n) {
        if (n % 2 == 0) {
            return n;
        }
        return n * 2;
    }
}
