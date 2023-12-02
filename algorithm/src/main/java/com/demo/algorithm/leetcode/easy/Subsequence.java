package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/22
 * @author chenglong
 * description : 判断子序列
 *
 * 给定字符串s和t，判断s是否为t的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 *
 * 提示：
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 */
public class Subsequence {

    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (t.length() < s.length()) {
            return false;
        }
        int index1 = 0;
        int index2 = 0;
        int m = s.length();
        int n = t.length();
        while (index1 < m && index2 < n) {
            if (s.charAt(index1) == t.charAt(index2)) {
                index1++;
                index2++;
                if (index1 == m) {
                    return true;
                }
            } else {
                index2++;
            }
        }
        return false;
    }
}
