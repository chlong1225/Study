package com.demo.algorithm.leetcode;

/**
 * create by chenglong on 9/9/21
 * description : 验证回文串
 *
 *  给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 */
public class PalindromeStr {

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        int start = 0;
        int end = s.length() - 1;
        char a = s.charAt(start);
        char b = s.charAt(end);
        while (start < end) {
            if (isLetterOrNumber(a) && isLetterOrNumber(b)) {
                if (isSame(a, b)) {
                    start++;
                    end--;
                    a = s.charAt(start);
                    b = s.charAt(end);
                } else {
                    return false;
                }
            } else if (isLetterOrNumber(a)) {
                end--;
                b = s.charAt(end);
            } else if (isLetterOrNumber(b)) {
                start++;
                a = s.charAt(start);
            } else {
                start++;
                end--;
                a = s.charAt(start);
                b = s.charAt(end);
            }
        }
        return true;
    }

    //判断两个字符忽略大小写是否相同
    public static boolean isSame(char a, char b) {
        if (a == b) {
            return true;
        }
        if (a >= '0' && a <= '9' || (b >= '0' && b <= '9')) {
            return false;
        }
        return Math.abs(a - b) == 32;
    }

    //判断当前字符是否为字母或数字
    private static boolean isLetterOrNumber(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}
