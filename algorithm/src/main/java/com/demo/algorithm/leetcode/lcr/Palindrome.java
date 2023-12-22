package com.demo.algorithm.leetcode.lcr;

/**
 * create on 2023/12/22
 * @author chenglong
 * description : LCR 018. 验证回文串
 *
 * 给定一个字符串s，验证s是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 本题中，将空字符串定义为有效的回文串。
 *
 * 示例 1:
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 *
 * 示例 2:
 * 输入: s = "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *
 * 提示：
 * 1 <= s.length <= 2 * 10^5
 * 字符串s由ASCII字符组成
 */
public class Palindrome {

    public boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        int end = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            if (checkLetter(s.charAt(i))) {
                //从尾部查找字符
                while (end >= 0) {
                    if (checkLetter(s.charAt(end))) {
                        break;
                    } else {
                        end--;
                    }
                }
                if (end == -1) {
                    return false;
                }
                if (end == i) {
                    return true;
                }
                if (end < i) {
                    break;
                }
                if (!isSame(s.charAt(i), s.charAt(end))) {
                    return false;
                }
                end--;
            }
        }
        return true;
    }

    //检查是否满足条件
    private boolean checkLetter(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }

    //判断a，b字符是否相等，忽略大小写
    private boolean isSame(char a, char b) {
        if (a >= '0' && a <= '9') {
            return a == b;
        }
        //此时a为字母
        if (b >= '0' && b <= '9') {
            return false;
        }
        int diff = 'a' - 'A';
        //将a，b转大写
        if (a >= 'a' && a <= 'z') {
            a -= diff;
        }
        if (b >= 'a' && b <= 'z') {
            b -= diff;
        }
        return a == b;
    }
}
