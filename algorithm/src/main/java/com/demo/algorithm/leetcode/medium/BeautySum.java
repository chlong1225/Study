package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/12/12
 * @author chenglong
 * description : 所有子字符串美丽值之和
 *
 * 一个字符串的美丽值定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 * 比方说，"abaacc"的美丽值为3 - 1 = 2。
 * 给你一个字符串s，请你返回它所有子字符串的美丽值之和。
 *
 * 示例 1：
 * 输入：s = "aabcb"
 * 输出：5
 * 解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。
 *
 * 示例 2：
 * 输入：s = "aabcbaa"
 * 输出：17
 *
 * 提示：
 * 1 <= s.length <= 500
 * s只包含小写英文字母。
 */
public class BeautySum {

    public int beautySum(String s) {
        int length = s.length();
        if (length < 3) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < length - 2; i++) {
            int[] counts = new int[26];
            counts[s.charAt(i) - 'a']++;
            counts[s.charAt(i + 1) - 'a']++;
            int max = 1;
            int min = max;
            if (s.charAt(i) == s.charAt(i + 1)) {
                max++;
            }
            for (int j = i + 2; j < length; j++) {
                int index = s.charAt(j) - 'a';
                counts[index]++;
                //更新最大值
                if (counts[index] > max) {
                    max = counts[index];
                }
                //更新最小值
                min = max;
                for (int k = 0; k < 26; k++) {
                    if (counts[k] != 0 && min > counts[k]) {
                        min = counts[k];
                    }
                }
                sum += (max - min);
            }
        }
        return sum;
    }
}
