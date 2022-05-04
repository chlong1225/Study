package com.demo.algorithm.leetcode.contest.doubleweek75;

/**
 * Created by chl on 2022/4/12.
 * description : 构造字符串的总得分和
 *
 * 你需要从空字符串开始构造一个长度为n的字符串s，构造的过程为每次给当前字符串前面添加一个字符。
 * 构造过程中得到的所有字符串编号为1到n，其中长度为i的字符串编号为si。
 * 比方说，s="abaca"，s1 == "a"，s2=="ca"，s3=="aca"依次类推。
 * si的得分为si和sn的最长公共前缀的长度（注意s == sn）。
 * 给你最终的字符串s，请你返回每一个si的得分之和。
 *
 * 示例 1：
 * 输入：s = "babab"
 * 输出：9
 * 解释：
 * s1 == "b" ，最长公共前缀是 "b" ，得分为 1 。
 * s2 == "ab" ，没有公共前缀，得分为 0 。
 * s3 == "bab" ，最长公共前缀为 "bab" ，得分为 3 。
 * s4 == "abab" ，没有公共前缀，得分为 0 。
 * s5 == "babab" ，最长公共前缀为 "babab" ，得分为 5 。
 * 得分和为 1 + 0 + 3 + 0 + 5 = 9 ，所以我们返回 9 。
 *
 * 示例 2 ：
 * 输入：s = "azbazbzaz"
 * 输出：14
 * 解释：
 * s2 == "az" ，最长公共前缀为 "az" ，得分为 2 。
 * s6 == "azbzaz" ，最长公共前缀为 "azb" ，得分为 3 。
 * s9 == "azbazbzaz" ，最长公共前缀为 "azbazbzaz" ，得分为 9 。
 * 其他 si 得分均为 0 。
 * 得分和为 2 + 3 + 9 = 14 ，所以我们返回 14 。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s只包含小写英文字母。
 */
public class SumScores {

    public long sumScores(String s) {
        /**
         * 使用扩展KMP算法,即Z数组
         * https://oi-wiki.org/string/z-func/
         */
        int n = s.length();
        int[] marks = new int[n];
        int start = 0;
        int end = 0;
        for (int i = 1; i < n; i++) {
            marks[i] = Math.max(Math.min(marks[i - start], end - i + 1), 0);
            while (i + marks[i] < n && s.charAt(marks[i]) == s.charAt(marks[i] + i)) {
                start = i;
                end = i + marks[i];
                marks[i]++;
            }
        }
        long sum = n;
        for (int i = 0; i < n; i++) {
            sum += marks[i];
        }
        return sum;
    }
}
