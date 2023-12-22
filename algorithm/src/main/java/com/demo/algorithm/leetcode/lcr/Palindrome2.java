package com.demo.algorithm.leetcode.lcr;

/**
 * create on 2023/12/22
 * @author chenglong
 * description : LCR 019. 验证回文串II
 *
 * 给定一个非空字符串s，请判断如果最多从字符串中删除一个字符能否得到一个回文字符串。
 *
 * 示例 1:
 * 输入: s = "aba"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "abca"
 * 输出: true
 * 解释: 可以删除 "c" 字符 或者 "b" 字符
 *
 * 示例 3:
 * 输入: s = "abc"
 * 输出: false
 *
 * 提示:
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 */
public class Palindrome2 {

    public boolean validPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return isPalindrome(start + 1, end, s) || isPalindrome(start, end - 1, s);
            }
        }
        return true;
    }

    private boolean isPalindrome(int start, int end, String s) {
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}
