package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2021/12/12.
 * description : 转换成小写字母
 *
 * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 *
 * 示例 1：
 * 输入：s = "Hello"
 * 输出："hello"
 *
 * 示例 2：
 * 输入：s = "here"
 * 输出："here"
 *
 * 示例 3：
 * 输入：s = "LOVELY"
 * 输出："lovely"
 *  
 * 提示：
 * 1 <= s.length <= 100
 * s 由 ASCII 字符集中的可打印字符组成
 */
public class SwitchChar {

    public String toLowerCase(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int length = s.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                builder.append((char) (s.charAt(i) + 32));
            } else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
