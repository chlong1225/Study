package com.demo.algorithm.leetcode.easy;

/**
 * create on 11/23/21
 * @author chenglong
 * description : 亲密字符串
 *
 * 给你两个字符串s和goal ，只要我们可以通过交换s中的两个字母得到与goal相等的结果，就返回true ；否则返回 false 。
 * 交换字母的定义是：取两个下标i和j（下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
 * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 *  
 * 示例 1：
 * 输入：s = "ab", goal = "ba"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
 *
 * 示例 2：
 * 输入：s = "ab", goal = "ab"
 * 输出：false
 * 解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
 *
 * 示例 3：
 * 输入：s = "aa", goal = "aa"
 * 输出：true
 * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
 *
 * 示例 4：
 * 输入：s = "aaaaaaabc", goal = "aaaaaaacb"
 * 输出：true
 *  
 * 提示：
 * 1 <= s.length, goal.length <= 2 * 104
 * s 和 goal 由小写英文字母组成
 */
public class InitmateStr {

    public boolean buddyStrings(String s, String goal) {
        int length = s.length();
        //1,判断两个字符串长度是否相等，不等肯定无法交换相等
        if (goal.length() != length) {
            return false;
        }
        //2，特殊场景
        if (length == 1) {
            return false;
        }
        //3，count记录字符串不相同的数量，i，j分别记录第一个，第二个不相同的位置，用于后序交换
        int count = 0;
        int i = 0;
        int j = 0;
        //4，统计每个字符出现的次数
        int[] counts = new int[26];
        for (int k = 0; k < length; k++) {
            counts[s.charAt(k) - 'a']++;
            if (s.charAt(k) != goal.charAt(k)) {
                count++;
                if (count == 1) {
                    i = k;
                } else if (count == 2) {
                    j = k;
                } else {
                    //5，不相同的字符超过2个无法交换相等
                    return false;
                }
            }
        }
        //5，此时不相同的字符count只可能0，1，2
        if (count == 1) {
            return false;
        }
        if (count == 2) {
            if (s.charAt(i) == goal.charAt(j) && s.charAt(j) == goal.charAt(i)) {
                return true;
            }
            return false;
        }
        //6，字符都相同时，判断字符串是否有相同的两个字符，这样交换字符串不变
        for (int k = 0; k < 26; k++) {
            if (counts[k] >= 2) {
                return true;
            }
        }
        return false;
    }
}
