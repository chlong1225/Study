package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/11/22
 * @author chenglong
 * description : 第N个神奇数字
 *
 * 一个正整数如果能被a或b整除，那么它是神奇的。
 * 给定三个整数n,a,b，返回第n个神奇的数字。因为答案可能很大，所以返回答案对10^9+7取模后的值。
 *
 * 示例 1：
 * 输入：n = 1, a = 2, b = 3
 * 输出：2
 *
 * 示例2：
 * 输入：n = 4, a = 2, b = 3
 * 输出：6
 *
 * 提示：
 * 1 <= n <= 10^9
 * 2 <= a, b <= 4 * 10^4
 */
public class NthMagicalNumber {

    private static final int MOD = 1000000007;

    public int nthMagicalNumber(int n, int a, int b) {
        //1，特殊场景
        if (n == 1) {
            return Math.min(a, b);
        }
        //2，根据n的取值范围，无法使用双指针直接模拟，需要使用二分查找
        long min = Math.min(a, b);
        long max = n * min;
        //获取a，b的最小公倍数
        int c = getMinMultiple(a, b);
        while (min < max) {
            long middle = (max - min) / 2 + min;
            //计算min～middle之间满足条件的数量
            long count = middle / a + middle / b - middle / c;
            if (count < n) {
                min = middle + 1;
            } else {
                max = middle;
            }
        }
        return (int) (max%MOD);
    }

    private int getMinMultiple(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
