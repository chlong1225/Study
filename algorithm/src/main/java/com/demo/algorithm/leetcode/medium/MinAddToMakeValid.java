package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/10/4
 * @author chenglong
 * description : 使括号有效的最少添加
 *
 * 只有满足下面几点之一，括号字符串才是有效的：
 * 它是一个空字符串，或者
 * 它可以被写成AB（A与B连接）,其中A和B都是有效字符串，或者
 * 它可以被写作(A)，其中A是有效字符串。
 * 给定一个括号字符串s，移动N次，你就可以在字符串的任何位置插入一个括号。
 * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))"。
 * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
 *
 * 示例 1：
 * 输入：s = "())"
 * 输出：1
 *
 * 示例 2：
 * 输入：s = "((("
 * 输出：3
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 只包含'('和')'字符。
 */
public class MinAddToMakeValid {

    public int minAddToMakeValid(String s) {
        int count = 0;
        int left = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                if (left > 0) {
                    left--;
                } else {
                    count++;
                }
            }
        }
        count += left;
        return count;
    }
}
