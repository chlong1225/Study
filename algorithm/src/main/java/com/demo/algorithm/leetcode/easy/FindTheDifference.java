package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/5
 * @author chenglong
 * description : 找不同
 *
 * 给定两个字符串s和t，它们只包含小写字母。
 * 字符串t由字符串s随机重排，然后在随机位置添加一个字母。
 * 请找出在t中被添加的字母。
 *
 * 示例 1：
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 *
 * 示例 2：
 * 输入：s = "", t = "y"
 * 输出："y"
 *
 * 提示：
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 */
public class FindTheDifference {

    public char findTheDifference(String s, String t) {
        if (s.length() == 0) {
            return t.charAt(0);
        }
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            if (counts[index] == 0) {
                return t.charAt(i);
            }
            counts[index]--;
        }
        return 'a';
    }
}
