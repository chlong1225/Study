package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/1/22
 * @author chenglong
 * description : 解码字母到整数映射
 *
 * 给你一个字符串s，它由数字（'0' - '9'）和 '#' 组成。我们希望按下述规则将s映射为一些小写英文字符：
 * 字符（'a' - 'i'）分别用（'1' - '9'）表示。
 * 字符（'j' - 'z'）分别用（'10#' - '26#'）表示。
 * 返回映射之后形成的新字符串。
 * 题目数据保证映射始终唯一。
 *
 * 示例 1：
 * 输入：s = "10#11#12"
 * 输出："jkab"
 * 解释："j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
 *
 * 示例 2：
 * 输入：s = "1326#"
 * 输出："acz"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s[i] 只包含数字（'0'-'9'）和 '#' 字符。
 * s 是映射始终存在的有效字符串。
 */
public class FreqAlphabets {

    public String freqAlphabets(String s) {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        int n = s.length();
        while (index < n) {
            if (index + 2 < n && s.charAt(index + 2) == '#') {
                char c = (char) (Integer.parseInt(s.substring(index, index + 2)) - 10 + 'j');
                builder.append(c);
                index += 3;
            } else {
                char c = (char) (s.charAt(index) - '1' + 'a');
                builder.append(c);
                index++;
            }
        }
        return builder.toString();
    }
}
