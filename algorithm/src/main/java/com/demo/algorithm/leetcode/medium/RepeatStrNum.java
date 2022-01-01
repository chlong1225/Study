package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/12/22.
 * description : 重复叠加字符串匹配
 *
 * 给定两个字符串a和b，寻找重复叠加字符串a的最小次数，使得字符串b成为叠加后的字符串a的子串，如果不存在则返回 -1。
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 *
 * 示例 1：
 * 输入：a = "abcd", b = "cdabcdab"
 * 输出：3
 * 解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
 *
 * 示例 2：
 * 输入：a = "a", b = "aa"
 * 输出：2
 *
 * 示例 3：
 * 输入：a = "a", b = "a"
 * 输出：1
 *
 * 示例 4：
 * 输入：a = "abc", b = "wxyz"
 * 输出：-1
 *  
 * 提示：
 * 1 <= a.length <= 104
 * 1 <= b.length <= 104
 * a 和 b 由小写英文字母组成
 */
public class RepeatStrNum {

    public int repeatedStringMatch(String a, String b) {
        int n = a.length();
        int m = b.length();
        //1，a多次重叠后，b是重叠后的子串。则b中的字符a均包含
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            counts[a.charAt(i) - 'a']++;
        }
        for (int i = 0; i < m; i++) {
            int index = b.charAt(i) - 'a';
            if (counts[index] == 0) {
                return -1;
            }
        }
        //如果能够重叠,最小的重叠次数
        int count = m / n;
        //2，b拆分成三部分：a的后部分 + n个a + a的前部分。最多count+2次
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(a);
        }
        for (int i = 0; i <= 2; i++) {
            if (!builder.toString().isEmpty()) {
                if (builder.toString().contains(b)) {
                    return count + i;
                }
            }
            builder.append(a);
        }
        return -1;
    }
}
