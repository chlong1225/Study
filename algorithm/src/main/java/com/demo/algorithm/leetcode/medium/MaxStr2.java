package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/3/5.
 * description : 最长特殊序列II
 *
 * 给定字符串列表strs，返回它们中最长的特殊序列。如果最长特殊序列不存在，返回 -1 。
 * 最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
 * s的子序列可以通过删去字符串s中的某些字符实现。
 * 例如，"abc"是 "aebdc"的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc"。
 * "aebdc"的子序列还包括"aebdc"、 "aeb"和 ""(空字符串)。
 *
 * 示例 1：
 * 输入: strs = ["aba","cdc","eae"]
 * 输出: 3
 *
 * 示例 2:
 * 输入: strs = ["aaa","aaa","aa"]
 * 输出: -1
 *
 * 提示:
 * 2 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i]只包含小写英文字母
 */
public class MaxStr2 {

    public int findLUSlength(String[] strs) {
        /**
         * 由于数据量少，这里直接暴力循环比较，同时记录当前字符串是否有效
         * 无效字符串：存在相同的字符串或当前字符串是其它字符串的子集
         */
        int length = strs.length;
        //用于记录当前字符串是否无效
        boolean[] marks = new boolean[length];
        for (int i = 0; i < length - 1; i++) {
            if (marks[i]) {
                continue;
            }
            String compare = strs[i];
            for (int j = i + 1; j < length; j++) {
                if (marks[j]) {
                    continue;
                }
                if (compare.equals(strs[j])) {
                    //两个字符串相同，同时失效
                    marks[i] = true;
                    marks[j] = true;
                } else {
                    if (isContain(compare, strs[j])) {
                        marks[j] = true;
                    }
                }
            }
        }
        int max = -1;
        for (int i = 0; i < length; i++) {
            if (!marks[i]) {
                //当前字符串有效
                if (strs[i].length() > max) {
                    max = strs[i].length();
                }
            }
        }
        return max;
    }

    private boolean isContain(String compare, String str) {
        if (compare.length() <= str.length()) {
            return false;
        }
        int length = str.length();
        int start = 0;
        for (int i = 0; i < length; i++) {
            int index = find(str.charAt(i), start, compare);
            if (index == -1) {
                return false;
            }
            start = index + 1;
        }
        return true;
    }

    private int find(char c, int start, String compare) {
        for (int i = start; i < compare.length(); i++) {
            if (compare.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }
}
