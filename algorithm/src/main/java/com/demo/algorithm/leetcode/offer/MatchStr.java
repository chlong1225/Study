package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/23.
 * description : 剑指Offer19. 正则表达式匹配
 *
 * 请实现一个函数用来匹配包含'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释:因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释:".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释:因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母以及字符.和*，无连续的 '*'。
 */
public class MatchStr {

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        /**
         * 使用动态规划：
         */
        int n = s.length();
        int m = p.length();
        //1，定义动态规划记录的数组
        boolean[][] marks = new boolean[n + 1][m + 1];
        //2，初始化状态
        marks[0][0] = true;
        for (int i = 1; i <= m; i++) {
            if (p.charAt(i - 1) == '*') {
                if (i == 1) {
                    marks[0][i] = true;
                } else {
                    marks[0][i] = marks[0][i - 1] || marks[0][i - 2];
                }
            }
        }
        //3，遍历，并执行转移方程
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    marks[i][j] = marks[i][j - 1];
                    if (j >= 2) {
                        marks[i][j] |= marks[i][j - 2];
                        if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                            marks[i][j] |= marks[i - 1][j];
                        }
                    }
                } else {
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        marks[i][j] |= marks[i - 1][j - 1];
                    }
                }
            }
        }
        return marks[n][m];
    }
}
