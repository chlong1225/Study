package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2024/1/22
 * description : 将整数转换为两个无零整数的和
 *
 *「无零整数」是十进制表示中不含任何0的正整数。
 * 给你一个整数n，请你返回一个由两个整数组成的列表[A,B]，满足：
 * A和B都是无零整数
 * A + B = n
 * 题目数据保证至少有一个有效的解决方案。
 * 如果存在多个有效解决方案，你可以返回其中任意一个。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：[1,1]
 * 解释：A = 1, B = 1. A + B = n 并且 A 和 B 的十进制表示形式都不包含任何 0 。
 *
 * 示例 2：
 * 输入：n = 11
 * 输出：[2,9]
 *
 * 示例 3：
 * 输入：n = 10000
 * 输出：[1,9999]
 *
 * 示例 4：
 * 输入：n = 69
 * 输出：[1,68]
 *
 * 示例 5：
 * 输入：n = 1010
 * 输出：[11,999]
 *
 * 提示：
 * 2 <= n <= 10^4
 */
public class NoZeroIntegers {

    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i <= n / 2; i++) {
            if (checkNum(i) && checkNum(n - i)) {
                return new int[]{i, n - i};
            }
        }
        return null;
    }

    private boolean checkNum(int num) {
        while (num > 0) {
            int tem = num % 10;
            num /= 10;
            if (tem == 0) {
                return false;
            }
        }
        return true;
    }
}
