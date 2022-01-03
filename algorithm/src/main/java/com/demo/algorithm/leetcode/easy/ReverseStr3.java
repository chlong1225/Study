package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/1/3.
 * description : 反转字符串中的单词III
 *
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *  
 * 提示：
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class ReverseStr3 {

    public String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        StringBuilder word = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == ' ') {
                word.reverse();
                builder.append(word.toString());
                builder.append(" ");
                word.delete(0, word.length());
            } else {
                word.append(s.charAt(i));
            }
        }
        if (word.length() > 0) {
            builder.append(word.reverse());
        }
        return builder.toString();
    }
}
