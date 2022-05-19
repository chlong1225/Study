package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/5/19.
 * description : 颠倒字符串中的单词
 *
 * 给你一个字符串s ，颠倒字符串中单词的顺序。
 * 单词是由非空格字符组成的字符串。s中使用至少一个空格将字符串中的单词分隔开。
 * 返回单词顺序颠倒且单词之间用单个空格连接的结果字符串。
 * 注意：输入字符串s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 *
 * 示例 2：
 * 输入：s = " hello world "
 * 输出："world hello"
 * 解释：颠倒后的字符串中不能存在前导空格和尾随空格。
 *
 * 示例 3：
 * 输入：s = "a good  example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，颠倒后的字符串需要将单词间的空格减少到仅有一个。
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个单词
 */
public class ReverseWords {

    public String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        int length = s.length();
        int end = -1;
        int start = -1;
        for (int i = length - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (start > 0) {
                    //单词：start~end
                    if (builder.length() > 0) {
                        builder.append(" ");
                    }
                    builder.append(s.substring(start, end + 1));
                    start = -1;
                    end = -1;
                }
            } else {
                if (end == -1) {
                    end = i;
                }
                start = i;
            }
        }
        if (start >= 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(s.substring(start, end + 1));
        }
        return builder.toString();
    }
}
