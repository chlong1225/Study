package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/12/16.
 * description : 不同的子序列
 *
 * 给定一个字符串s和一个字符串t，计算在s的子序列中t出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * 示例 1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 *
 * 示例 2：
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 *  
 * 提示：
 * 0 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 */
public class SubsequenceNum {

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        //1，t字符串的长度大于s字符串时，不可能存在子串，返回0
        if (n < m) {
            return 0;
        }
        //2，s字符串与t字符串长度相同时，字符串相同时为1，否则0
        if (m == n) {
            return s.equals(t) ? 1 : 0;
        }
        //3，s字符串长度大于t字符串
        /**
         * 对应字符串匹配问题.当前字符选取,不选取两种场景,可以使用动态规划.
         * marks[i][j]: i:选取t中的字符串数量 ; j:选取s中字符串的数量 ; marks[i][j]:子串的数量
         * 状态转移方程: 当前位置的子串数量 = marks[i][j-1](s不选择j字符时) + mark[i-1][j-1](s使用j字符时)
         * 使用j字符转移时,只有s的j字符与t的i字符相同时转移有效
         */
        int[][] marks = new int[m + 1][n + 1];
        //初始化状态: t字符串为空时,s字符串任意长度时均有一个空子串
        for (int i = 0; i <= n; i++) {
            marks[0][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //不使用字符s.charAt[j-1]时,转移到前一个位置
                marks[i][j] = marks[i][j - 1];
                //使用字符s.chatAt[j-1].
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    marks[i][j] += marks[i - 1][j - 1];
                }
            }
        }
        return marks[m][n];
    }
}
