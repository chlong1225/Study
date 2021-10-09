package com.demo.algorithm.leetcode.hard;

import com.demo.algorithm.algo.Calculation;

/**
 * create on 10/9/21
 * @author chenglong
 * description : 最长有效括号
 *
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 *  
 * 提示：
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 */
public class MaxBrackets {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        int max = 0;
        for (int i = 0; i < length; i++) {
            if (i + max > length) {
                break;
            }
            if (chars[i] == '(') {
                int validCount = getMaxVail(i, chars);
                if (validCount > max) {
                    max = validCount;
                }
            }
        }
        return max;
    }

    private int getMaxVail(int start, char[] chars) {
        int length = chars.length;
        int result = 0;
        int count = 0;
        for (int i = start; i < length; i++) {
            if (chars[i] == '(') {
                count++;
            } else if (chars[i] == ')') {
                count--;
                if (count == 0) {
                    if (result < (i - start + 1)) {
                        result = i - start + 1;
                    }
                } else if (count < 0) {
                    break;
                }
            }
        }
        return result;
    }
}
