package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/8
 * @author chenglong
 * description : 验证回文串II
 *
 * 给你一个字符串s，最多可以从中删除一个字符。
 * 请你判断s是否能成为回文字符串：如果能，返回true；否则，返回false。
 *
 * 示例 1：
 * 输入：s = "aba"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "abca"
 * 输出：true
 * 解释：你可以删除字符'c'。
 *
 * 示例 3：
 * 输入：s = "abc"
 * 输出：false
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 */
public class ValidPalindrome {

    public boolean validPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return isPaline(start + 1, end, s) || isPaline(start, end - 1, s);
            } else {
                start++;
                end--;
            }
        }
        return true;
    }

    private boolean isPaline(int start, int end, String s) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
