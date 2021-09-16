package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2021/9/5.
 * description : 最后一个单词的长度
 *
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 *
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 *
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 */
public class LastWordLength {

    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = 0;
        String tem;
        for (int i = s.length() - 1; i >= 0; i--) {
            tem = s.substring(i, i + 1);
            if (" ".equals(tem)) {
                if (length > 0) {
                    return length;
                }
            } else {
                length++;
            }
        }
        return length;
    }

}
