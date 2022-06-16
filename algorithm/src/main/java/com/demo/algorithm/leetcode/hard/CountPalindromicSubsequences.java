package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2022/6/10.
 * description : 统计不同回文子序列
 *
 * 给定一个字符串s，返回s中不同的非空「回文子序列」个数 。
 * 通过从s中删除0个或多个字符来获得子序列。
 * 如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。
 * 如果有某个i, 满足ai!= bi，则两个序列a1, a2, ...和b1, b2, ...不同。
 *
 * 注意：
 * 结果可能很大，你需要对10^9+ 7取模 。
 *
 * 示例 1：
 * 输入：s = 'bccb'
 * 输出：6
 * 解释：6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
 * 注意：'bcb' 虽然出现两次但仅计数一次。
 *
 * 示例 2：
 * 输入：s = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 * 输出：104860361
 * 解释：共有 3104860382 个不同的非空回文子序列，104860361 对 109 + 7 取模后的值。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s[i]仅包含'a','b','c'或'd'
 */
public class CountPalindromicSubsequences {

    private static final int MOD = 1000000007;

    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        //1，处理特殊情况
        if (n == 1) {
            return 1;
        }
        //2，使用动态规则按照字符串的区间进行转移
        int[][][] marks = new int[4][n][n];
        for (int i = 0; i < n; i++) {
            //指定位置单个字符数量为1
            marks[s.charAt(i) - 'a'][i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n + 1 - len; i++) {
                int left = i;
                int right = left + len - 1;
                for (int j = 0; j < 4; j++) {
                    if (s.charAt(left) == s.charAt(right)) {
                        int index = s.charAt(left) - 'a';
                        if (index == j) {
                            marks[j][left][right] = ((marks[0][left + 1][right - 1] + marks[1][left + 1][right - 1])) % MOD;
                            marks[j][left][right] += ((marks[2][left + 1][right - 1] + marks[3][left + 1][right - 1])) % MOD;
                            marks[j][left][right] += 2;
                            marks[j][left][right] %= MOD;
                        } else {
                            marks[j][left][right] = marks[j][left + 1][right - 1];
                        }
                    } else {
                        int indexLeft = s.charAt(left) - 'a';
                        int indexRight = s.charAt(right) - 'a';
                        if (indexLeft == j) {
                            marks[j][left][right] = marks[j][left][right - 1];
                        } else if (indexRight == j) {
                            marks[j][left][right] = marks[j][left + 1][right];
                        } else {
                            marks[j][left][right] = marks[j][left + 1][right - 1];
                        }
                    }
                }
            }
        }
        int sum = marks[0][0][n - 1];
        for (int i = 1; i < 4; i++) {
            sum += marks[i][0][n - 1];
            sum %= MOD;
        }
        return sum;
    }
}
