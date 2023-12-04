package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2023/12/4
 * description : 字符串中的第一个唯一字符
 *
 * 给定一个字符串s，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回-1。
 *
 * 示例 1：
 * 输入: s = "leetcode"
 * 输出: 0
 *
 * 示例 2:
 * 输入: s = "loveleetcode"
 * 输出: 2
 *
 * 示例 3:
 * 输入: s = "aabb"
 * 输出: -1
 *
 * 提示:
 * 1 <= s.length <= 10^5
 * s 只包含小写字母
 */
public class FirstUniqChar {

    public int firstUniqChar(String s) {
        //1，统计字符出现的次数 + 第一次出现的index
        int[] counts = new int[26];
        int[] firstIndexs = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (counts[index] == 0) {
                firstIndexs[index] = i;
            }
            counts[index]++;
        }
        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (counts[i] == 1) {
                if (minIndex > firstIndexs[i]) {
                    minIndex = firstIndexs[i];
                }
            }
        }
        if (minIndex == Integer.MAX_VALUE) {
            return -1;
        }
        return minIndex;
    }
}
