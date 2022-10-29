package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/10/17
 * @author chenglong
 * description : 不同的子序列II
 *
 * 给定一个字符串s，计算s的不同非空子序列的个数。因为结果可能很大，所以返回答案需要对10^9+7取余。
 * 字符串的子序列是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 *
 * 示例 1：
 * 输入：s = "abc"
 * 输出：7
 * 解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
 *
 * 示例 2：
 * 输入：s = "aba"
 * 输出：6
 * 解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
 *
 * 示例 3：
 * 输入：s = "aaa"
 * 输出：3
 * 解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
 *
 * 提示：
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class DistinctSubseqII {

    private static final int MOD = 1000000007;

    public int distinctSubseqII(String s) {
        int[] marks = new int[26];
        int length = s.length();
        marks[s.charAt(0) - 'a'] = 1;
        for (int i = 1; i < length; i++) {
            int index = s.charAt(i) - 'a';
            int tem = 1;
            for (int j = 0; j < 26; j++) {
                tem += marks[j];
                tem %= MOD;
            }
            marks[index] = tem;
        }
        int total = 0;
        for (int i = 0; i < 26; i++) {
            total += marks[i];
            total %= MOD;
        }
        return total;
    }
}
