package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/5
 * @author chenglong
 * description : 最长回文串
 *
 * 给定一个包含大写字母和小写字母的字符串s，返回通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如"Aa"不能当做一个回文字符串。
 *
 * 示例 1:
 * 输入:s = "abccccdd"
 * 输出:7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是7。
 *
 * 示例 2:
 * 输入:s = "a"
 * 输出:1
 *
 * 示例 3：
 * 输入:s = "aaaaaccc"
 * 输出:7
 *
 * 提示:
 * 1 <= s.length <= 2000
 * s 只由小写和/或大写英文字母组成
 */
public class LongestPalindrome {

    public int longestPalindrome(String s) {
        //1，统计大小写字母的数量
        int[] counts1 = new int[26];
        int[] counts2 = new int[26];
        int n = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                counts2[s.charAt(i) - 'A']++;
            } else {
                counts1[s.charAt(i) - 'a']++;
            }
        }
        int total = 0;
        for (int i = 0; i < 26; i++) {
            total += (counts1[i] / 2);
            total += (counts2[i] / 2);
        }
        total *= 2;
        if (total < n) {
            total++;
        }
        return total;
    }
}
