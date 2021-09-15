package com.demo.algorithm.leetcode;

/**
 * create by chenglong on 9/15/21
 * description : 计数质数
 *
 *  统计所有小于非负整数 n 的质数的数量。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 *
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 *  
 * 提示：
 * 0 <= n <= 5 * 106
 */
public class CountPrimes {

    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        int count = 1;
        for (int i = 3; i < n; i+=2) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    //判断当前数是否为质数
    private static boolean isPrime(int number) {
        int divisor = 3;
        while ((number % divisor) != 0 && (divisor < number / divisor)) {
            divisor += 2;
        }
        if (number % divisor == 0) {
            return number == divisor;
        }
        return true;
    }
}
