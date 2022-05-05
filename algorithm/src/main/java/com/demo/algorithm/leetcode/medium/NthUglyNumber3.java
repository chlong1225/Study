package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/5/3.
 * description :  丑数III
 *
 * 给你四个整数：n 、a 、b 、c ，请你设计一个算法来找出第n个丑数。
 * 丑数是可以被a或b或c整除的正整数 。
 *
 * 示例 1：
 * 输入：n = 3, a = 2, b = 3, c = 5
 * 输出：4
 * 解释：丑数序列为 2, 3, 4, 5, 6, 8, 9, 10... 其中第 3 个是 4。
 *
 * 示例 2：
 * 输入：n = 4, a = 2, b = 3, c = 4
 * 输出：6
 * 解释：丑数序列为 2, 3, 4, 6, 8, 9, 10, 12... 其中第 4 个是 6。
 *
 * 示例 3：
 * 输入：n = 5, a = 2, b = 11, c = 13
 * 输出：10
 * 解释：丑数序列为 2, 4, 6, 8, 10, 11, 12, 13... 其中第 5 个是 10。
 *
 * 示例 4：
 * 输入：n = 1000000000, a = 2, b = 217983653, c = 336916467
 * 输出：1999999984
 *
 * 提示：
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * 本题结果在[1,2*10^9]的范围内
 */
public class NthUglyNumber3 {

    public int nthUglyNumber(int n, int a, int b, int c) {
        /**
         * 使用二分查找
         */
        long ab = getLcm(a, b);
        long ac = getLcm(a, c);
        long bc = getLcm(b, c);
        long abc = getLcm(ab, c);
        long start = 1;
        long end = 2000000000;
        while (start < end) {
            long middle = (end - start) / 2 + start;
            //计算start~middle之间满足条件的数量
            long sum1 = middle / a + middle / b + middle / c;
            long sum2 = middle / ab + middle / bc + middle / ac;
            long sum3 = middle / abc;
            long sum = sum1 - sum2 + sum3;
            if (sum >= n) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return (int) start;
    }

    //获取最小公倍数
    private long getLcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    //获取a，b最大公约数
    private long gcd(long a, long b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
