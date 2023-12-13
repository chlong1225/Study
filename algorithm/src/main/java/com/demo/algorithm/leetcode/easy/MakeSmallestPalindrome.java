package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/13
 * @author chenglong
 * description : 字典序最小回文串
 *
 * 给你一个由小写英文字母组成的字符串s，你可以对其执行一些操作。在一步操作中，你可以用其他小写英文字母替换s中的一个字符。
 * 请你执行尽可能少的操作，使s变成一个回文串。如果执行最少操作次数的方案不止一种，则只需选取字典序最小的方案。
 * 对于两个长度相同的字符串a和b，在a和b出现不同的第一个位置，如果该位置上a中对应字母比b中对应字母在字母表中出现顺序更早，则认为a的字典序比b的字典序要小。
 * 返回最终的回文字符串。
 *
 * 示例 1：
 * 输入：s = "egcfe"
 * 输出："efcfe"
 * 解释：将 "egcfe" 变成回文字符串的最小操作次数为 1 ，修改 1 次得到的字典序最小回文字符串是 "efcfe"，只需将 'g' 改为 'f' 。
 *
 * 示例 2：
 * 输入：s = "abcd"
 * 输出："abba"
 * 解释：将 "abcd" 变成回文字符串的最小操作次数为 2 ，修改 2 次得到的字典序最小回文字符串是 "abba" 。
 *
 * 示例 3：
 * 输入：s = "seven"
 * 输出："neven"
 * 解释：将 "seven" 变成回文字符串的最小操作次数为 1 ，修改 1 次得到的字典序最小回文字符串是 "neven" 。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
public class MakeSmallestPalindrome {

    public String makeSmallestPalindrome(String s) {
        int n = s.length();
        if (n == 1) {
            return s;
        }
        char[] dates = new char[n];
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) > s.charAt(n - 1 - i)) {
                dates[i] = s.charAt(n - 1 - i);
                dates[n - 1 - i] = s.charAt(n - 1 - i);
            } else {
                dates[i] = s.charAt(i);
                dates[n - 1 - i] = s.charAt(i);
            }
        }
        if (n % 2 == 1) {
            dates[n / 2] = s.charAt(n / 2);
        }
        return new String(dates);
    }
}
