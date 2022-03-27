package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer48. 最长不含重复字符的子字符串
 *
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 * 示例1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 * 请注意，你的答案必须是子串的长度，"pwke"是一个子序列，不是子串。
 *
 * 提示：
 * s.length <= 40000
 * s 由英文字母、数字、符号和空格组成
 */
public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        if (length == 1) {
            return 1;
        }
        int max = 1;
        int start = 0;
        int end = 0;
        int[] counts = new int[128];
        while (end < length) {
            int index = s.charAt(end);
            counts[index]++;
            end++;
            if (counts[index] > 1) {
                int count = end - start - 1;
                if (count > max) {
                    max = count;
                }
                //start开始删除字符
                while (start < length) {
                    int indexStart = s.charAt(start);
                    start++;
                    counts[indexStart]--;
                    if (counts[indexStart] > 0) {
                        break;
                    }
                }
            }
        }
        int count = end - start;
        if (count > max) {
            max = count;
        }
        return max;
    }
}
