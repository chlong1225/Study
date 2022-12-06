package com.demo.algorithm.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * create on 2022/12/6
 * @author chenglong
 * description : 字符串中不同整数的数目
 *
 * 给你一个字符串word，该字符串由数字和小写英文字母组成。
 * 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成"123 34 8 34" 。注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）："123"、"34"、"8" 和 "34" 。
 * 返回对word完成替换后形成的不同整数的数目。
 * 只有当两个整数的不含前导零的十进制表示不同，才认为这两个整数也不同。
 *
 * 示例 1：
 * 输入：word = "a123bc34d8ef34"
 * 输出：3
 * 解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
 *
 * 示例 2：
 * 输入：word = "leet1234code234"
 * 输出：2
 *
 * 示例 3：
 * 输入：word = "a1b01c001"
 * 输出：1
 * 解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
 *
 * 提示：
 * 1 <= word.length <= 1000
 * word 由数字和小写英文字母组成
 */
public class NumDifferentIntegers {

    public int numDifferentIntegers(String word) {
        Set<String> marks = new HashSet<>();
        int length = word.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char cur = word.charAt(i);
            if (cur >= '0' && cur <= '9') {
                builder.append(cur);
            } else {
                if (builder.length() > 0) {
                    marks.add(check(builder));
                    builder.delete(0, builder.length());
                }
            }
        }
        if (builder.length() > 0) {
            marks.add(check(builder));
        }
        return marks.size();
    }

    private String check(StringBuilder builder) {
        //查找第一个不是0的index
        int find = -1;
        int length = builder.length();
        for (int i = 0; i < length; i++) {
            if (builder.charAt(i) != '0') {
                find = i;
                break;
            }
        }
        if (find == -1) {
            return "0";
        }
        return builder.substring(find);
    }
}
