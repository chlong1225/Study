package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/3/6
 * @author chenglong
 * description : 使字符串平衡的最少删除次数
 *
 * 给你一个字符串s，它仅包含字符'a'和'b'。
 * 你可以删除s中任意数目的字符，使得s平衡。当不存在下标对(i,j)满足i < j，且s[i]='b'的同时s[j]= 'a'，此时认为s是平衡的。
 * 请你返回使s平衡的最少删除次数。
 *
 * 示例 1：
 * 输入：s = "aababbab"
 * 输出：2
 * 解释：你可以选择以下任意一种方案：
 * 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
 * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
 *
 * 示例 2：
 * 输入：s = "bbaaaaabb"
 * 输出：2
 * 解释：唯一的最优解是删除最前面两个字符。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i]要么是'a' 要么是'b'。
 */
public class MinimumDeletions {

    public int minimumDeletions(String s) {
        int length = s.length();
        /**
         * marks[i][0]对应当前位置为a时删除的字母数量
         * marks[i][1]对应当前位置为b时删除的字母数量
         */
        int[][] marks = new int[length][2];
        boolean hasB = false;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (hasB) {
                if (c == 'a') {
                    marks[i][0] = marks[i - 1][0];
                    marks[i][1] = marks[i - 1][1] + 1;
                } else {
                    marks[i][0] = marks[i - 1][0] + 1;
                    marks[i][1] = Math.min(marks[i - 1][0], marks[i - 1][1]);
                }
            } else {
                if (c == 'b') {
                    hasB = true;
                    marks[i][0] = 1;
                }
            }
        }
        return Math.min(marks[length - 1][0], marks[length - 1][1]);
    }
}
