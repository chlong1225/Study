package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/22
 * @author chenglong
 * description : 斐波那契数
 *
 * 斐波那契数（通常用F(n)表示）形成的序列称为斐波那契数列。该数列由0和1开始，后面的每一项数字都是前面两项数字的和。也就是：
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定n，请计算F(n)。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 *
 * 示例 3：
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 *
 * 提示：
 *
 * 0 <= n <= 30
 */
public class Fib {

    private static int[] marks;

    static {
        marks = new int[31];
        marks[0] = 0;
        marks[1] = 1;
        for (int i = 2; i <= 30; i++) {
            marks[i] = marks[i - 1] + marks[i - 2];
        }
    }

    public int fib(int n) {
        return marks[n];
    }
}
