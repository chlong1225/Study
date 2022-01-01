package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/12/23.
 * description : 最长重复子串
 *
 * 给你一个字符串s，考虑其所有重复子串 ：即，s的连续子串，在s中出现2次或更多次。这些出现之间可能存在重叠。
 * 返回任意一个可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 *
 * 示例 1：
 * 输入：s = "banana"
 * 输出："ana"
 *
 * 示例 2：
 * 输入：s = "abcd"
 * 输出：""
 *  
 * 提示：
 * 2 <= s.length <= 3 * 104
 * s 由小写英文字母组成
 */
public class MaxRepeatStr {

    public String longestDupSubstring(String s) {
        int lenght = s.length();
        for (int i = lenght - 1; i >= 1; i--) {
            //i:子串的长度
            String str = findStr(i, s);
            if (!str.isEmpty()) {
                return str;
            }
        }
        return "";
    }

    //暴力枚举比较,会超时.
    private String findStr(int count, String s) {
        int length = s.length();
        int maxIndex = length - count;
        for (int i = 0; i <= maxIndex; i++) {
            String s1 = s.substring(i, i + count);
            int start = i + 1;
            while (start <= maxIndex) {
                String tem = s.substring(start, start + count);
                if (s1.equals(tem)) {
                    return s1;
                }
                start++;
            }
        }
        return "";
    }
}
