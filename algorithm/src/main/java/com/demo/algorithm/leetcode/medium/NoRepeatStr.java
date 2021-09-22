package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/9/19.
 * description : 无重复字符的最长字串。一次循环+2个索引定位
 *
 *  示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 *  
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class NoRepeatStr {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        if (length == 1) {
            return 1;
        }
        int maxLength = 1;
        int startIndex = 0;
        int nextIndex = 1;
        char tem;
        while (nextIndex < length) {
            tem = s.charAt(nextIndex);
            int index = hasSame(s.substring(startIndex, nextIndex), tem);
            if (index == -1) {
                nextIndex++;
            } else {
                int space = nextIndex - startIndex;
                if (space > maxLength) {
                    maxLength = space;
                }
                startIndex = startIndex + index + 1;
                nextIndex++;
            }
        }
        if (nextIndex == length) {
            int space = nextIndex - startIndex;
            if (space > maxLength) {
                maxLength = space;
            }
        }
        return maxLength;
    }

    private static int hasSame(String str, char tem) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (tem == str.charAt(i)) {
                return i;
            }
        }
        return -1;
    }

}
