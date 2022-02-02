package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/2/1.
 * description : 最长的美好子字符串
 *
 * 当一个字符串s包含的每一种字母的大写和小写形式同时出现在s中，就称这个字符串s是美好字符串。
 * 比方说，"abABB"是美好字符串，因为'A'和'a'同时出现了，且'B'和'b'也同时出现了。
 * 然而，"abA"不是美好字符串因为'b'出现了，而'B'没有出现。
 * 给你一个字符串s，请你返回s最长的美好子字符串。如果有多个答案，请你返回最早出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
 *
 * 示例 1：
 * 输入：s = "YazaAay"
 * 输出："aAa"
 * 解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
 * "aAa" 是最长的美好子字符串。
 *
 * 示例 2：
 * 输入：s = "Bb"
 * 输出："Bb"
 * 解释："Bb" 是美好字符串，因为'B'和'b'都出现了。整个字符串也是原字符串的子字符串。
 *
 * 示例 3：
 * 输入：s = "c"
 * 输出：""
 * 解释：没有美好子字符串。
 *
 * 示例 4：
 * 输入：s = "dDzeE"
 * 输出："dD"
 * 解释："dD" 和 "eE" 都是最长美好子字符串。
 * 由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。
 *
 * 提示：
 * 1 <= s.length <= 100
 * s只包含大写和小写英文字母。
 */
public class MaxLengthNiceStr {

    public String longestNiceSubstring(String s) {
        int length = s.length();
        if (length == 1) {
            return "";
        }
        for (int i = length; i >= 2; i--) {
            String str = findNiceStr(i, s);
            if (str.length() > 0) {
                return str;
            }
        }
        return "";
    }

    //在字符串s中查找长度为n的美好子串
    private String findNiceStr(int n, String s) {
        //记录小写字母的数量
        int[] lowercase = new int[26];
        //记录大写字母的数量
        int[] uppercase = new int[26];
        //记录非美好字符数量
        int diffCount = 0;
        //初始化统计定长字符串
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                //小写字母
                int index = s.charAt(i) - 'a';
                if (lowercase[index] == 0) {
                    if (uppercase[index] > 0) {
                        diffCount--;
                    } else {
                        diffCount++;
                    }
                }
                lowercase[index]++;
            } else {
                //大写字母
                int index = s.charAt(i) - 'A';
                if (uppercase[index] == 0) {
                    if (lowercase[index] == 0) {
                        diffCount++;
                    } else {
                        diffCount--;
                    }
                }
                uppercase[index]++;
            }
        }
        if (diffCount == 0) {
            return s.substring(0, n);
        }
        int length = s.length();
        //滑动最后一个字符
        for (int i = n; i < length; i++) {
            //添加字符
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                int addIndex = s.charAt(i) - 'a';
                if (lowercase[addIndex] == 0) {
                    if (uppercase[addIndex] > 0) {
                        diffCount--;
                    } else {
                        diffCount++;
                    }
                }
                lowercase[addIndex]++;
            } else {
                int addIndex = s.charAt(i) - 'A';
                if (uppercase[addIndex] == 0) {
                    if (lowercase[addIndex] == 0) {
                        diffCount++;
                    } else {
                        diffCount--;
                    }
                }
                uppercase[addIndex]++;
            }
            //移除之前的字符
            if (s.charAt(i - n) >= 'a' && s.charAt(i - n) <= 'z') {
                int deleteIndex = s.charAt(i - n) - 'a';
                if (lowercase[deleteIndex] == 1) {
                    if (uppercase[deleteIndex] == 0) {
                        diffCount--;
                    } else {
                        diffCount++;
                    }
                }
                lowercase[deleteIndex]--;
            } else {
                int deleteIndex = s.charAt(i - n) - 'A';
                if (uppercase[deleteIndex] == 1) {
                    if (lowercase[deleteIndex] == 0) {
                        diffCount--;
                    } else {
                        diffCount++;
                    }
                }
                uppercase[deleteIndex]--;
            }
            if (diffCount == 0) {
                int start = i + 1 - n;
                return s.substring(start, i + 1);
            }
        }
        return "";
    }
}
