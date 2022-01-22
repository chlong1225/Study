package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/1/22.
 * description : 删除回文子序列
 *
 * 给你一个字符串s，它仅由字母'a'和'b'组成。每一次删除操作都可以从s中删除一个回文子序列。
 * 返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
 *「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，
 * 那么这个字符串就是原字符串的一个子序列。
 * 「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
 *
 * 示例 1：
 * 输入：s = "ababa"
 * 输出：1
 * 解释：字符串本身就是回文序列，只需要删除一次。
 *
 * 示例 2：
 * 输入：s = "abb"
 * 输出：2
 * 解释："abb" -> "bb" -> "".
 * 先删除回文子序列 "a"，然后再删除 "bb"。
 *
 * 示例 3：
 * 输入：s = "baabb"
 * 输出：2
 * 解释："baabb" -> "b" -> "".
 * 先删除回文子序列 "baab"，然后再删除 "b"。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s仅包含字母'a'和'b'
 */
public class DeletePalindromeStr {

    public int removePalindromeSub(String s) {
        /**
         * 分析：如果当前字符串本身是回文，则一次删除结束。否则需要多次删除。
         * 注意：这里每次删除的是子序列而不是回文串。即baabb可以直接删除bbb。剩余aa。
         * 根据只有a，b两种字母，最多需要删除两次。最终转换为判断当前字符串是否为回文
         */
        int length = s.length();
        for (int i = 0; i < length / 2; i++) {
            if (s.charAt(i) != s.charAt(length - 1 - i)) {
                return 2;
            }
        }
        return 1;
    }
}
