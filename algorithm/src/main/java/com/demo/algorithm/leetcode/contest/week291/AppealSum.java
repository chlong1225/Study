package com.demo.algorithm.leetcode.contest.week291;

/**
 * Created by chl on 2022/5/8.
 * description : 字符串的总引力
 *
 * 字符串的引力定义为：字符串中不同字符的数量。
 * 例如，"abbca" 的引力为3 ，因为其中有3个不同字符'a'、'b'和'c'。
 * 给你一个字符串s ，返回其所有子字符串的总引力 。
 * 子字符串定义为：字符串中的一个连续字符序列。
 *
 * 示例 1：
 * 输入：s = "abbca"
 * 输出：28
 * 解释："abbca" 的子字符串有：
 * - 长度为 1 的子字符串："a"、"b"、"b"、"c"、"a" 的引力分别为 1、1、1、1、1，总和为 5 。
 * - 长度为 2 的子字符串："ab"、"bb"、"bc"、"ca" 的引力分别为 2、1、2、2 ，总和为 7 。
 * - 长度为 3 的子字符串："abb"、"bbc"、"bca" 的引力分别为 2、2、3 ，总和为 7 。
 * - 长度为 4 的子字符串："abbc"、"bbca" 的引力分别为 3、3 ，总和为 6 。
 * - 长度为 5 的子字符串："abbca" 的引力为 3 ，总和为 3 。
 * 引力总和为 5 + 7 + 7 + 6 + 3 = 28 。
 * 
 * 示例 2：
 * 输入：s = "code"
 * 输出：20
 * 解释："code" 的子字符串有：
 * - 长度为 1 的子字符串："c"、"o"、"d"、"e" 的引力分别为 1、1、1、1 ，总和为 4 。
 * - 长度为 2 的子字符串："co"、"od"、"de" 的引力分别为 2、2、2 ，总和为 6 。
 * - 长度为 3 的子字符串："cod"、"ode" 的引力分别为 3、3 ，总和为 6 。
 * - 长度为 4 的子字符串："code" 的引力为 4 ，总和为 4 。
 * 引力总和为 4 + 6 + 6 + 4 = 20 。
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 */
public class AppealSum {

    //使用朴素枚举的解法，会超时
    public long appealSum(String s) {
        int n = s.length();
        //1，单个字符的子串引力为1
        long sum = n;
        for (int i = 2; i <= n; i++) {
            //不同长度的子串，使用滑动窗口进行统计
            int[] counts = new int[26];
            long diff = 0;
            for (int j = 0; j < i; j++) {
                int index = s.charAt(j) - 'a';
                if (counts[index] == 0) {
                    diff++;
                }
                counts[index]++;
            }
            sum += diff;
            for (int j = i; j < n; j++) {
                int delete = s.charAt(j - i) - 'a';
                int add = s.charAt(j) - 'a';
                if (counts[delete] == 1) {
                    diff--;
                }
                counts[delete]--;
                if (counts[add] == 0) {
                    diff++;
                }
                counts[add]++;
                sum += diff;
            }
        }
        return sum;
    }
}
