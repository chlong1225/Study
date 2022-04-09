package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/4/5.
 * description : 二进制表示中质数个计算置位
 *
 * 给你两个整数left和right ，在闭区间[left, right]范围内，统计并返回计算置位位数为质数的整数个数。
 * 计算置位位数就是二进制表示中1的个数。
 * 例如， 21的二进制表示10101有3个计算置位。
 *
 *示例 1：
 * 输入：left = 6, right = 10
 * 输出：4
 * 解释：
 * 6 -> 110 (2 个计算置位，2 是质数)
 * 7 -> 111 (3 个计算置位，3 是质数)
 * 9 -> 1001 (2 个计算置位，2 是质数)
 * 10-> 1010 (2 个计算置位，2 是质数)
 * 共计 4 个计算置位为质数的数字。
 *
 * 示例 2：
 * 输入：left = 10, right = 15
 * 输出：5
 * 解释：
 * 10 -> 1010 (2 个计算置位, 2 是质数)
 * 11 -> 1011 (3 个计算置位, 3 是质数)
 * 12 -> 1100 (2 个计算置位, 2 是质数)
 * 13 -> 1101 (3 个计算置位, 3 是质数)
 * 14 -> 1110 (3 个计算置位, 3 是质数)
 * 15 -> 1111 (4 个计算置位, 4 不是质数)
 * 共计 5 个计算置位为质数的数字。
 *
 * 提示：
 * 1 <= left <= right <= 10^6
 * 0 <= right - left <= 10^4
 */
public class CountPrimeSetBits {

    public static final boolean[] MARKS = {false, false, true, true, false, true, false, true, false,
            false, false, true, false, true, false, false, false,
            true, false, true, false, false};

    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            int num = getCount(i);
            if (MARKS[num]) {
                count++;
            }
        }
        return count;
    }

    //统计计算置位位数
    private int getCount(int num) {
        int count = 0;
        while (num > 0) {
            if (num % 2 == 1) {
                count++;
            }
            num /= 2;
        }
        return count;
    }

    public int countPrimeSetBits2(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            int num = Integer.bitCount(i);
            if (MARKS[num]) {
                count++;
            }
        }
        return count;
    }
}
