package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/17.
 * description : 剑指Offer44. 数字序列中某一位的数字
 *
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 *
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 *
 * 限制：
 * 0 <= n <2^31
 */
public class FindNthDigit {

    private static int[] counts = new int[10];

    static {
        counts[1] = 10;
        int modulus = 10;
        for (int i = 2; i < 10; i++) {
            if (i < 9) {
                counts[i] = i * 9 * modulus;
                modulus *= 10;
            } else {
                counts[i] = Integer.MAX_VALUE;
            }
        }
    }

    public int findNthDigit(int n) {
        /**
         * 分析：统计每一个位数对应的数字个数。最终转为：数字num中第k位的数字
         */
        if (n <= 9) {
            return n;
        }
        int count = 1;
        while (n >= 0) {
            if (n >= counts[count]) {
                n -= counts[count];
                count++;
            } else {
                int add = n / count;
                int index = n % count;
                int num = 1;
                for (int i = 0; i < count - 1; i++) {
                    num *= 10;
                }
                num += add;
                String str = "" + num;
                return str.charAt(index) - '0';
            }
        }
        return 0;
    }
}
