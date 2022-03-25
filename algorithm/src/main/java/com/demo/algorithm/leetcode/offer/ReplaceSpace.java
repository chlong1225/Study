package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/25.
 * description : 剑指Offer 05. 替换空格
 *
 * 请实现一个函数，把字符串s中的每个空格替换成"%20"。
 *
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 限制：
 * 0 <= s 的长度 <= 10000
 */
public class ReplaceSpace {

    public String replaceSpace(String s) {
        StringBuilder builder = new StringBuilder();
        int length = s.length();
        if (length == 0) {
            return builder.toString();
        }
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == ' ') {
                builder.append("%20");
            } else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
