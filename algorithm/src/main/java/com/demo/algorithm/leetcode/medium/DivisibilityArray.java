package com.demo.algorithm.leetcode.medium;

/**
 * create on 2024/3/9
 * @author chenglong
 * description : 找出字符串的可整除数组
 *
 * 给你一个下标从0开始的字符串word，长度为n，由从0到9的数字组成。另给你一个正整数m。
 * word的可整除数组div是一个长度为n的整数数组，并满足：
 * 如果 word[0,...,i] 所表示的 数值 能被 m 整除，div[i] = 1
 * 否则，div[i] = 0
 * 返回 word 的可整除数组。
 *
 * 示例 1：
 * 输入：word = "998244353", m = 3
 * 输出：[1,1,0,0,0,1,1,0,0]
 * 解释：仅有 4 个前缀可以被 3 整除："9"、"99"、"998244" 和 "9982443" 。
 *
 * 示例 2：
 * 输入：word = "1010", m = 10
 * 输出：[0,1,0,1]
 * 解释：仅有 2 个前缀可以被 10 整除："10" 和 "1010" 。
 *
 * 提示：
 * 1 <= n <= 10^5
 * word.length == n
 * word 由数字0到9组成
 * 1 <= m <= 10^9
 */
public class DivisibilityArray {

    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] results = new int[n];
        long num = 0;
        for (int i = 0; i < n; i++) {
            int cur = word.charAt(i) - '0';
            num = num * 10 + cur;
            if (num % m == 0) {
                results[i] = 1;
                num = 0;
            } else {
                num %= m;
            }
        }
        return results;
    }
}
