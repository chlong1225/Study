package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/4/16.
 * description : 最大回文数乘积
 *
 * 给定一个整数n，返回可表示为两个n位整数乘积的最大回文整数。因为答案可能非常大，所以返回它对1337取余 。
 *
 * 示例 1:
 * 输入：n = 2
 * 输出：987
 * 解释：99 x 91 = 9009, 9009 % 1337 = 987
 *
 * 示例 2:
 * 输入： n = 1
 * 输出： 9
 *
 * 提示:
 * 1 <= n <= 8
 */
public class LargestPalindrome {

    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        int max = (int) (Math.pow(10, n) - 1);
        for (int i = max; i >= 0; i--) {
            //i对应的回文
            long num = i;
            int x = i;
            while (x > 0) {
                num = num * 10 + x % 10;
                x /= 10;
            }
            for (long j = max; j * j >= num; j--) {
                if (num % j == 0 ) {
                    return (int) (num % 1337);
                }
            }
        }
        return 0;
    }
}
