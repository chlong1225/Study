package com.demo.algorithm.leetcode.contest.doubleweek78;

/**
 * Created by chl on 2022/5/16.
 * description : 最大波动的子字符串
 *
 * 字符串的波动定义为子字符串中出现次数最多的字符次数与出现次数最少的字符次数之差。
 * 给你一个字符串s，它只包含小写英文字母。请你返回s里所有子字符串的最大波动值。
 * 子字符串是一个字符串的一段连续字符序列。
 *
 * 示例 1：
 * 输入：s = "aababbb"
 * 输出：3
 * 解释：
 * 所有可能的波动值和它们对应的子字符串如以下所示：
 * - 波动值为 0 的子字符串："a" ，"aa" ，"ab" ，"abab" ，"aababb" ，"ba" ，"b" ，"bb" 和 "bbb" 。
 * - 波动值为 1 的子字符串："aab" ，"aba" ，"abb" ，"aabab" ，"ababb" ，"aababbb" 和 "bab" 。
 * - 波动值为 2 的子字符串："aaba" ，"ababbb" ，"abbb" 和 "babb" 。
 * - 波动值为 3 的子字符串 "babbb" 。
 * 所以，最大可能波动值为 3 。
 *
 * 示例 2：
 * 输入：s = "abcde"
 * 输出：0
 * 解释：
 * s 中没有字母出现超过 1 次，所以 s 中每个子字符串的波动值都是 0 。
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s只包含小写英文字母。
 */
public class LargestVariance {

    public int largestVariance(String s) {
        /**
         * 枚举子串改为枚举获取波动值时对应的字符
         */
        int max = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                if (i == j) {
                    continue;
                }
                int diff = getMaxDiff(i, j, s);
                if (max < diff) {
                    max = diff;
                }
            }
        }
        return max;
    }

    //获取s子串中a，b字符数量差值的最大值
    private int getMaxDiff(char a, char b, String s) {
        int length = s.length();
        int count1 = 0;
        int count2 = 0;
        int max = 0;
        boolean hasB = false;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == a) {
                count1++;
            } else if (s.charAt(i) == b) {
                count2++;
            } else {
                continue;
            }
            if (count2 > 0) {
                if (count1 >= count2) {
                    int diff = count1 - count2;
                    if (max < diff) {
                        max = diff;
                    }
                } else {
                    //之前的子串无效，数据重置
                    count1 = 0;
                    count2 = 0;
                    hasB = true;
                }
            }
        }
        //补充特殊场景：后半段只有a。而之前由于b多余a，子串无效。造成无法计算diff(b统计为0)
        if (count2 == 0) {
            if (count1 > 0 && hasB) {
                if (max < count1 - 1) {
                    max = count1 - 1;
                }
            }
        }
        return max;
    }
}
