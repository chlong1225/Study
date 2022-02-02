package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/2/2.
 * description : 反转单词前缀
 *
 * 给你一个下标从0开始的字符串word和一个字符ch。找出ch第一次出现的下标i，反转word中从下标0开始、直到下标i结束（含下标i）的那段字符。如果word中不存在字符ch，则无需进行任何操作。
 * 例如，如果word="abcdefd"且ch="d"，那么你应该反转从下标0开始、直到下标3结束（含下标3）。结果字符串将会是"dcbaefd" 。
 * 返回结果字符串 。
 *
 * 示例 1：
 * 输入：word = "abcdefd", ch = "d"
 * 输出："dcbaefd"
 * 解释："d" 第一次出现在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "dcbaefd" 。
 *
 * 示例 2：
 * 输入：word = "xyxzxe", ch = "z"
 * 输出："zxyxxe"
 * 解释："z" 第一次也是唯一一次出现是在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "zxyxxe" 。
 *
 * 示例 3：
 * 输入：word = "abcd", ch = "z"
 * 输出："abcd"
 * 解释："z" 不存在于 word 中。
 * 无需执行反转操作，结果字符串是 "abcd" 。
 *
 * 提示：
 * 1 <= word.length <= 250
 * word 由小写英文字母组成
 * ch 是一个小写英文字母
 */
public class ReversePrefix {

    public String reversePrefix(String word, char ch) {
        int length = word.length();
        int index = -1;
        for (int i = 0; i < length; i++) {
            if (word.charAt(i) == ch) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return word;
        }
        StringBuilder builder = new StringBuilder();
        if (index == 0) {
            return word;
        }
        builder.append(word.substring(0, index + 1));
        builder.reverse();
        if (index != length - 1) {
            builder.append(word.substring(index + 1));
        }
        return builder.toString();
    }
}
