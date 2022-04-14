package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/14.
 * description : 剑指Offer16. 数值的整数次方
 *
 * 实现pow(x,n)，即计算x的n次幂函数（即，x^n）。不得使用库函数，同时不需要考虑大数问题。
 *
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 * 提示：
 * -100.0< x <100.0
 * -2^31<= n <=2^31-1
 * -10^4<= x^n<= 10^4
 */
public class MyPow {

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double extra = 1;
        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                extra = 1 / x;
                n++;
            }
            n = -n;
            x = 1 / x;
        }
        double sum = 1;
        double add = x;
        while (n > 0) {
            if (n % 2 == 1) {
                sum *= add;
            }
            n /= 2;
            add *= add;
        }
        return sum * extra;
    }
}
