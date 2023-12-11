package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2023/12/10
 * description : 计数二进制子串
 *
 * 给定一个字符串s，统计并返回具有相同数量0和1的非空（连续）子字符串的数量，并且这些子字符串中的所有0和所有1都是成组连续的。
 * 重复出现（不同位置）的子串也要统计它们出现的次数。
 *
 * 示例 1：
 * 输入：s = "00110011"
 * 输出：6
 * 解释：6 个子串满足具有相同数量的连续 1 和 0 ："0011"、"01"、"1100"、"10"、"0011" 和 "01" 。
 * 注意，一些重复出现的子串（不同位置）要统计它们出现的次数。
 * 另外，"00110011" 不是有效的子串，因为所有的 0（还有 1 ）没有组合在一起。
 *
 * 示例 2：
 * 输入：s = "10101"
 * 输出：4
 * 解释：有 4 个子串："10"、"01"、"10"、"01" ，具有相同数量的连续 1 和 0 。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i]为'0'或'1'
 */
public class CountBinarySubstrings {

    public int countBinarySubstrings(String s) {
        int n = s.length();
        if (n == 1) {
            return 0;
        }
        int[] counts = new int[2];
        counts[s.charAt(0) - '0']++;
        int total = 0;
        for (int i = 1; i < n; i++) {
            int index = s.charAt(i) - '0';
            if (s.charAt(i) == s.charAt(i - 1)) {
                counts[index]++;
            } else {
                if (index == 0) {
                    if (counts[0] == 0) {
                        //此时结构为：111...
                        counts[0]++;
                    } else {
                        //此时结构为：00...11...0。之前的0无效
                        total += Math.min(counts[0], counts[1]);
                        counts[0] = 1;
                    }
                } else {
                    if (counts[1] == 0) {
                        counts[1]++;
                    } else {
                        //此时结构为：11..00..1。之前的1无效
                        total += Math.min(counts[0], counts[1]);
                        counts[1] = 1;
                    }
                }
            }
        }
        total += Math.min(counts[0], counts[1]);
        return total;
    }
}
