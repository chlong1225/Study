package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/10/28
 * @author chenglong
 * description : 有效的字母异位词
 *
 * 给定两个字符串s和t，编写一个函数来判断t是否是s的字母异位词。
 * 注意：若s和t中每个字符出现的次数都相同，则称s和t互为字母异位词。
 *
 * 示例1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 提示:
 * 1 <= s.length, t.length <= 5 * 10^4
 * s和t仅包含小写字母
 * 进阶:如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class Anagram {

    public boolean isAnagram(String s, String t) {
        int length = s.length();
        if (t.length() != length) {
            return false;
        }
        int[] counts = new int[26];
        for (int i = 0; i < length; i++) {
            int index = s.charAt(i) - 'a';
            counts[index]++;
        }
        for (int i = 0; i < length; i++) {
            int index = t.charAt(i) - 'a';
            if (counts[index] > 0) {
                counts[index]--;
            } else {
                return false;
            }
        }
        return true;
    }
}
