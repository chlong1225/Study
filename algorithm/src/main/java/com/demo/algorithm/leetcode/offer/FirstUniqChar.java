package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/26.
 * description : 剑指Offer50. 第一个只出现一次的字符
 *
 * 在字符串s中找出第一个只出现一次的字符。如果没有，返回一个单空格。s只包含小写字母。
 *
 * 示例 1:
 * 输入：s = "abaccdeff"
 * 输出：'b'
 *
 * 示例 2:
 * 输入：s = ""
 * 输出：' '
 *
 * 限制：
 * 0 <= s 的长度 <= 50000
 */
public class FirstUniqChar {

    public char firstUniqChar(String s) {
        int length = s.length();
        if (length == 0) {
            return ' ';
        }
        //统计字符第一次出现的位置。超过一次，记录-2。初始条件为-1
        int[] counts = new int[26];
        for (int i = 0; i < 26; i++) {
            counts[i] = -1;
        }
        for (int i = 0; i < length; i++) {
            int index = s.charAt(i) - 'a';
            if (counts[index] == -1) {
                counts[index] = i;
            } else if (counts[index] >= 0) {
                //当前字符已经存在
                counts[index] = -2;
            }
        }
        int index = -1;
        char result = ' ';
        for (int i = 0; i < 26; i++) {
            if (counts[i] == -1 || counts[i] == -2) {
                continue;
            }
            if (index == -1) {
                index = counts[i];
                result = (char) ('a' + i);
            } else {
                if (index > counts[i]) {
                    index = counts[i];
                    result = (char) ('a' + i);
                }
            }
        }
        return result;
    }
}
