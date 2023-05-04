package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/5/4
 * @author chenglong
 * description : 检查替换后的词是否有效
 *
 * 给你一个字符串s，请你判断它是否有效。
 * 字符串s有效需要满足：假设开始有一个空字符串t=""，你可以执行任意次下述操作将t转换为s：
 * 将字符串"abc"插入到t中的任意位置。形式上，t变为tleft+"abc"+tright，其中t==tleft + tright 。注意，tleft和tright可能为空。
 * 如果字符串s有效，则返回true；否则，返回false。
 *
 * 示例 1：
 * 输入：s = "aabcbc"
 * 输出：true
 * 解释：
 * "" -> "abc" -> "aabcbc"
 * 因此，"aabcbc" 有效。
 *
 * 示例 2：
 * 输入：s = "abcabcababcc"
 * 输出：true
 * 解释：
 * "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 * 因此，"abcabcababcc" 有效。
 *
 * 示例 3：
 * 输入：s = "abccba"
 * 输出：false
 * 解释：执行操作无法得到 "abccba" 。
 *
 * 提示：
 * 1 <= s.length <= 2 * 10^4
 * s 由字母 'a'、'b' 和 'c' 组成
 */
public class Valid {

    public boolean isValid(String s) {
        while (s.length() > 0) {
            int index = s.indexOf("abc");
            if (index == -1) {
                return false;
            }
            StringBuilder builder = new StringBuilder();
            if (index > 0) {
                builder.append(s.substring(0, index));
            }
            if (index + 3 < s.length()) {
                builder.append(s.substring(index + 3));
            }
            s = builder.toString();
        }
        return true;
    }
}
