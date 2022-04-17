package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/17.
 * description : 剑指Offer43. 1～n 整数中1出现的次数
 *
 * 输入一个整数n，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：5
 * 
 * 示例 2：
 * 输入：n = 13
 * 输出：6
 *
 * 限制：
 * 1 <= n <2^31
 */
public class CountDigitOne {

    private static int[] counts = new int[10];

    static {
        counts[1] = 1;
        int add = 10;
        for (int i = 2; i < 10; i++) {
            counts[i] = counts[i - 1] * 10 + add;
            add *= 10;
        }
    }

    public int countDigitOne(int n) {
        /**
         * n的取值范围：1~2147483647
         * 预处理处：一位数字，两位数字中1的数量
         */

        int sum = 0;
        while (n > 0) {
            //统计n的位数
            int tem = n;
            int modulus = 1;
            int count = 0;
            while (tem > 0) {
                count++;
                tem /= 10;
            }
            for (int i = 0; i < count - 1; i++) {
                modulus *= 10;
            }
            //最高位数字
            int a = n / modulus;
            sum += (a * counts[count - 1]);
            if (a > 1) {
                sum += modulus;
            } else {
                sum += (n - modulus + 1);
            }
            n %= modulus;
        }
        return sum;
    }
}
