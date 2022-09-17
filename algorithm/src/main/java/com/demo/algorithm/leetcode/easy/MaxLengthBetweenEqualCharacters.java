package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/9/17
 * @author chenglong
 * description : 两个相同字符之间的最长子字符串
 *
 * 给你一个字符串s，请你返回两个相同字符之间的最长子字符串的长度，计算长度时不含这两个字符。如果不存在这样的子字符串，返回-1 。
 * 子字符串是字符串中的一个连续字符序列。
 *
 * 示例 1：
 * 输入：s = "aa"
 * 输出：0
 * 解释：最优的子字符串是两个 'a' 之间的空子字符串。
 *
 * 示例 2：
 * 输入：s = "abca"
 * 输出：2
 * 解释：最优的子字符串是 "bc" 。
 *
 * 示例 3：
 * 输入：s = "cbzxy"
 * 输出：-1
 * 解释：s 中不存在出现出现两次的字符，所以返回 -1 。
 *
 * 示例 4：
 * 输入：s = "cabbac"
 * 输出：4
 * 解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
 *
 * 提示：
 * 1 <= s.length <= 300
 * s 只含小写英文字母
 */
public class MaxLengthBetweenEqualCharacters {

    public int maxLengthBetweenEqualCharacters(String s) {
        int[] marks = new int[26];
        for (int i = 0; i < 26; i++) {
            marks[i] = -1;
        }
        int length = s.length();
        int max = -1;
        for (int i = 0; i < length; i++) {
            int index = s.charAt(i) - 'a';
            if (marks[index] == -1) {
                marks[index] = i;
            } else {
                int space = i - marks[index] - 1;
                if (space > max) {
                    max = space;
                }
            }
        }
        return max;
    }
}
