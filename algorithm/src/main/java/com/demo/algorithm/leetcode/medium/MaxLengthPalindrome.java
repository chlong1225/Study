package com.demo.algorithm.leetcode.medium;

/**
 * create by chenglong on 9/23/21
 * description : 最长回文子串
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 *
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 *  
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class MaxLengthPalindrome {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        if (length == 1) {
            return s;
        }
        String result = s.substring(0, 1);
        String tem;
        boolean[] isEnd = {false};
        for (int i = 0; i < length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                tem = findPalindrome(i, i + 1, chars, s, isEnd);
                if (tem.length() > result.length()) {
                    result = tem;
                }
            }
            tem = findPalindrome(i, i, chars, s, isEnd);
            if (tem.length() > result.length()) {
                result = tem;
            }
            if (isEnd[0]) {
                break;
            }
        }
        return result;
    }

    private static String findPalindrome(int startIndex, int endIndex, char[] chars, String s, boolean[] isEnd) {
        while (startIndex > 0 && endIndex < chars.length - 1 && (chars[startIndex - 1] == chars[endIndex + 1])) {
            startIndex--;
            endIndex++;
        }
        if (endIndex == chars.length - 1) {
            isEnd[0] = true;
        }
        return s.substring(startIndex, endIndex + 1);
    }
}
