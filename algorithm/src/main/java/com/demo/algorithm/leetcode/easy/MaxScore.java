package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/8/14.
 * description : 分割字符串的最大得分
 *
 * 给你一个由若干0和1组成的字符串s，请你计算并返回将该字符串分割成两个非空子字符串（即左子字符串和右子字符串）所能获得的最大得分。
 * 「分割字符串的得分」为左子字符串中0的数量加上右子字符串中1的数量。
 *
 * 示例 1：
 * 输入：s = "011101"
 * 输出：5
 * 解释：
 * 将字符串 s 划分为两个非空子字符串的可行方案有：
 * 左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 + 4 = 5
 * 左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4
 * 左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 = 3
 * 左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2
 * 左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
 *
 * 示例 2：
 * 输入：s = "00111"
 * 输出：5
 * 解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
 *
 * 示例 3：
 * 输入：s = "1111"
 * 输出：3
 *
 * 提示：
 * 2 <= s.length <= 500
 * 字符串 s 仅由字符 '0' 和 '1' 组成。
 */
public class MaxScore {

    public int maxScore(String s) {
        int length = s.length();
        //1，分别统计0和1的总数量
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '0') {
                count0++;
            } else {
                count1++;
            }
        }
        if (count0 == 0 || count1 == 0) {
            //全部是0或1
            return length - 1;
        }
        int max = 0;
        int left0 = 0;
        int left1 = 0;
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) == '0') {
                left0++;
            } else {
                left1++;
            }
            int sum = left0 + count1 - left1;
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
