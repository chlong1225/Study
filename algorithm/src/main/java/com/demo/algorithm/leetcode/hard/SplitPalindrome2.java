package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/12/19.
 * description : 分割回文串II
 *
 * 给你一个字符串s，请你将s分割成一些子串，使每个子串都是回文。
 * 返回符合要求的 最少分割次数 。
 *
 * 示例 1：
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * 示例 2：
 * 输入：s = "a"
 * 输出：0
 *
 * 示例 3：
 * 输入：s = "ab"
 * 输出：1
 *  
 * 提示：
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class SplitPalindrome2 {

    public int minCut(String s) {
        int length = s.length();
        /**
         * 使用动态规划:
         * marks[i]: i:字符的数量; marks[i]:对应字符i分割的最小次数
         * marks[i] = min(marks[i-1],marks[i-2]....marks[1]),并且对应的(i-k,i)是回文串
         */
        int[] marks = new int[length + 1];
        marks[0] = -1;
        marks[1] = 0;
        for (int i = 2; i <= length; i++) {
            int min = Integer.MAX_VALUE;
            //j:截取字符串后部分的数量
            for (int j = 1; j <= i; j++) {
                String tem = s.substring(i - j, i);
                if (isPartition(tem)) {
                    min = Math.min(min, marks[i - j] + 1);
                }
            }
            marks[i] = min;
        }
        return marks[length];
    }

    //判断当前字符串是否为回文串
    private boolean isPartition(String s) {
        int length = s.length();
        if (length == 1) {
            return true;
        }
        int count = length >> 1;
        for (int i = 0; i < count; i++) {
            if (s.charAt(i) != s.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
