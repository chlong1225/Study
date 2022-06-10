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
        int length = s.length();
        if (length == 1) {
            return 1;
        }
        /**
         * 使用动态规划：参数分别对于: 字母类型，即回文起始字符；字符串左边的位置；字符串右边的位置
         * 初始化：当前left=right时，当前只有一个字符，回文数为1
         */
        int[][][] marks = new int[4][length][length];
        //1，初始化条件
        for (int i = 0; i < length; i++) {
            marks[s.charAt(i) - 'a'][i][i] = 1;
        }
        //2，遍历字符串长度
        for (int i = 2; i <= length; i++) {
            //遍历起始点
            for (int j = 0; j <= length - i; j++) {
                int left = j;
                int right = i + j - 1;
                for (int k = 0; k < 4; k++) {
                    if (s.charAt(left) == s.charAt(right) && s.charAt(left) - 'a' == k) {
                        marks[k][left][right] = 2 + (marks[0][left + 1][right-1] + marks[1][left + 1][right-1]) % MOD
                                + (marks[2][left + 1][right-1] + marks[3][left + 1][right-1]) % MOD;
                        marks[k][j][i + j - 1] %= MOD;
                    } else if (s.charAt(left) - 'a' == k) {
                        marks[k][left][right] = marks[k][left][right - 1];
                    } else if (s.charAt(right) - 'a' == k) {
                        marks[k][left][right] = marks[k][left + 1][right];
                    } else {
                        marks[k][left][right] = marks[k][left + 1][right - 1];
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += marks[i][0][length - 1];
            sum %= MOD;
        }
        return sum;
    }
}
