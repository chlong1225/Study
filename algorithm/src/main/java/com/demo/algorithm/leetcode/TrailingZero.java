package com.demo.algorithm.leetcode;

/**
 * create by chenglong on 9/14/21
 * description : 阶乘后的零
 *
 *  给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *  进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
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
 * 0 <= n <= 104
 */
public class TrailingZero {

    public static int trailingZeroes(int n) {
        if (n < 5) {
            return 0;
        }
        int count0 = 0;
        int count2 = 0;
        int count5 = 0;
        for (int i = 2; i <= n; i++) {
            int tem = i;
            while (tem % 10 == 0) {
                count0++;
                tem /= 10;
            }
            while (tem % 2 == 0) {
                count2++;
                tem /= 2;
            }
            while (tem % 5 == 0) {
                count5++;
                tem /= 5;
            }
        }
        return count0 + Math.min(count2, count5);
    }

    public static int trailingZeroes2(int n) {
        if (n < 5) {
            return 0;
        }
        int count = 0;
        while (n / 5 != 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
