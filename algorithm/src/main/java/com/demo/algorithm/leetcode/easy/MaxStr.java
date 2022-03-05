package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/3/5.
 * description : 最长特殊序列I
 *
 * 给你两个字符串a和b，请返回这两个字符串中最长的特殊序列的长度。如果不存在，则返回 -1。
 *「最长特殊序列」定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
 * 字符串s的子序列是在从s中删除任意数量的字符后可以获得的字符串。
 * 例如，"abc" 是 "aebdc" 的子序列，因为删除 "aebdc" 中斜体加粗的字符可以得到 "abc" 。
 * "aebdc" 的子序列还包括 "aebdc" 、 "aeb" 和 "" (空字符串)。
 *
 * 示例 1：
 * 输入: a = "aba", b = "cdc"
 * 输出: 3
 * 解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。
 *
 * 示例 2：
 * 输入：a = "aaa", b = "bbb"
 * 输出：3
 * 解释: 最长特殊序列是 "aaa" 和 "bbb" 。
 *
 * 示例 3：
 * 输入：a = "aaa", b = "aaa"
 * 输出：-1
 * 解释: 字符串 a 的每个子序列也是字符串 b 的每个子序列。同样，字符串 b 的每个子序列也是字符串 a 的子序列。
 *
 * 提示：
 * 1 <= a.length, b.length <= 100
 * a和b由小写英文字母组成
 */
public class MaxStr {

    public int findLUSlength(String a, String b) {
        /**
         * 分析：如果a，b字符串长度不同，则较长的字符串肯定不可能在另外一个中找到，直接返该长度
         * 如果a，b长度相同，两个字符串不同，任意一个都不会在另一个中找到，直接返回长度
         * 如果a，b长度相同，字符串也相同。a中任意子串在b中都会找到，直接返回-1
         */
        if (a.length() != b.length()) {
            return Math.max(a.length(), b.length());
        }
        if (a.equals(b)) {
            return -1;
        }
        return a.length();
    }
}
