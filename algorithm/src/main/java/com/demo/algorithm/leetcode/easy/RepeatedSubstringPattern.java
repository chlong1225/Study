package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2023/12/5
 * description : 重复的子字符串
 *
 * 给定一个非空的字符串s，检查是否可以通过由它的一个子串重复多次构成。
 *
 * 示例 1:
 * 输入: s = "abab"
 * 输出: true
 * 解释: 可由子串 "ab" 重复两次构成。
 *
 * 示例 2:
 * 输入: s = "aba"
 * 输出: false
 *
 * 示例 3:
 * 输入: s = "abcabcabcabc"
 * 输出: true
 * 解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 */
public class RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        if (n == 1) {
            return false;
        }
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                //s必须是长度i的整数倍
                int count = n / i;
                boolean isSame = true;
                String compare = s.substring(0, i);
                for (int j = 1; j < count; j++) {
                    String child = s.substring(j * i, j * i + i);
                    if (!compare.equals(child)) {
                        isSame = false;
                        break;
                    }
                }
                if (isSame) {
                    return true;
                }
            }
        }
        return false;
    }
}
