package com.demo.algorithm.leetcode.contest.week305;

/**
 * create on 2022/8/9
 * @author chenglong
 * description : 最长理想子序列
 *
 * 给你一个由小写字母组成的字符串s，和一个整数k。如果满足下述条件，则可以将字符串t视作是理想字符串 ：
 * t是字符串s的一个子序列。
 * t中每两个相邻字母在字母表中位次的绝对差值小于或等于 k 。
 * 返回最长理想字符串的长度。
 * 字符串的子序列同样是一个字符串，并且子序列还满足：可以经由其他字符串删除某些字符（也可以不删除）但不改变剩余字符的顺序得到。
 * 注意：字母表顺序不会循环。例如，'a' 和 'z' 在字母表中位次的绝对差值是25，而不是1 。
 *
 * 示例 1：
 * 输入：s = "acfgbd", k = 2
 * 输出：4
 * 解释：最长理想字符串是 "acbd" 。该字符串长度为 4 ，所以返回 4 。
 * 注意 "acfgbd" 不是理想字符串，因为 'c' 和 'f' 的字母表位次差值为 3 。
 *
 * 示例 2：
 * 输入：s = "abcd", k = 3
 * 输出：4
 * 解释：最长理想字符串是 "abcd" ，该字符串长度为 4 ，所以返回 4 。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * 0 <= k <= 25
 * s由小写英文字母组成
 */
public class LongestIdealString {

    public int longestIdealString(String s, int k) {
        int length = s.length();
        int[] marks = new int[26];
        for (int i = 0; i < length; i++) {
            int index = s.charAt(i) - 'a';
            int preMax = 0;
            int min = Math.max(index - k, 0);
            int max = Math.min(index + k, 25);
            for (int j = min; j <= max; j++) {
                if (marks[j] > preMax) {
                    preMax = marks[j];
                }
            }
            marks[index] = preMax + 1;
        }
        int max = marks[0];
        for (int i = 1; i < 26; i++) {
            if (marks[i] > max) {
                max = marks[i];
            }
        }
        return max;
    }
}
