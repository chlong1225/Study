package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/6/27
 * @author chenglong
 * description : 最长特殊序列II
 *
 * 给定字符串列表strs，返回其中最长的特殊序列。如果最长特殊序列不存在，返回 -1 。
 * 特殊序列定义如下：该序列为某字符串独有的子序列（即不能是其他字符串的子序列）。
 * s的子序列可以通过删去字符串s中的某些字符实现。
 * 例如，"abc"是"aebdc"的子序列，因为您可以删除"aebdc"中的下划线字符来得到"abc"。"aebdc"的子序列还包括"aebdc"、 "aeb"和""(空字符串)。
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
public class FindLUSlength {

    public int findLUSlength(String[] strs) {
        int max = -1;
        int length = strs.length;
        for (int i = 0; i < length; i++) {
            String word = strs[i];
            //判断word是否为特殊序列
            boolean isContains = false;
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    continue;
                }
                if (containsAll(word, strs[j])) {
                    isContains = true;
                    break;
                }
            }
            if (!isContains) {
                if (word.length() > max) {
                    max = word.length();
                }
            }
        }
        return max;
    }

    private boolean containsAll(String compare, String dates) {
        int m = compare.length();
        int n = dates.length();
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (dates.charAt(i) == compare.charAt(index)) {
                index++;
            }
            if (index == m) {
                return true;
            }
        }
        return false;
    }
}
