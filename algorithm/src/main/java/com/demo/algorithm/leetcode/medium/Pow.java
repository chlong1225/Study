package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/10/26.
 * description : 实现pow(x, n) ，即计算x的n次幂函数（即，x^n）。
 *
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 *
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *  
 * 提示：
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= xn <= 10^4
 */
public class Pow {

    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double result = 1.0;
        double multiple = x;
        while (n / 2 != 0) {
            int tem = n % 2;
            if (tem == 1) {
                result *= multiple;
            } else if (tem == -1) {
                result *= (1.0 / multiple);
            }
            multiple *= multiple;
            n /= 2;
        }
        if (n == 1) {
            result *= multiple;
        } else if (n == -1) {
            result *= (1.0 / multiple);
        }
        return result;
    }
}
