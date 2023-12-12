package com.demo.algorithm.leetcode.medium;

/**
 * create by chenglong on 2023/12/12
 * description : 整数拆分
 *
 * 给定一个正整数n，将其拆分为k个正整数的和（k>=2），并使这些整数的乘积最大化。
 * 返回你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: n = 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例 2:
 * 输入: n = 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 * 提示:
 * 2 <= n <= 58
 */
public class IntegerBreak {

    public int integerBreak(int n) {
        //当拆分为n个时，每一个为1。此时乘积=1为最小
        int max = 1;
        for (int i = 2; i < n; i++) {
            //平均分配的值。剩余的值依次分配一个。此时有last个(a1+1)，有(i-last)个a1
            int a1 = n / i;
            int last = n - a1 * i;
            int total = 1;
            for (int j = 0; j < last; j++) {
                total *= (a1 + 1);
            }
            for (int j = 0; j < (i - last); j++) {
                total *= a1;
            }
            if (total > max) {
                max = total;
            }
        }
        return max;
    }
}
