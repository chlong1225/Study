package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/5/4
 * @author chenglong
 * description : 删除字符使频率相同
 *
 * 给你一个下标从0开始的字符串word，字符串只包含小写英文字母。你需要选择一个下标并删除下标处的字符，使得word中剩余每个字母出现频率相同。
 * 如果删除一个字母后，word中剩余所有字母的出现频率都相同，那么返回true，否则返回false。
 *
 * 注意：
 * 字母x的频率是这个字母在字符串中出现的次数。
 * 你必须恰好删除一个字母，不能一个字母都不删除。
 *
 * 示例 1：
 * 输入：word = "abcc"
 * 输出：true
 * 解释：选择下标 3 并删除该字母，word 变成 "abc" 且每个字母出现频率都为 1 。
 *
 * 示例 2：
 * 输入：word = "aazz"
 * 输出：false
 * 解释：我们必须删除一个字母，所以要么 "a" 的频率变为 1 且 "z" 的频率为 2 ，要么两个字母频率反过来。所以不可能让剩余所有字母出现频率相同。
 *
 * 提示：
 * 2 <= word.length <= 100
 * word只包含小写英文字母。
 */
public class EqualFrequency {

    public boolean equalFrequency(String word) {
        //1，统计字符出现的次数
        int[] counts = new int[26];
        for (int i = 0; i < word.length(); i++) {
            counts[word.charAt(i) - 'a']++;
        }
        //2，获取最多与最少的次数
        int minIndex = -1;
        int maxIndex = -1;
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                if (minIndex == -1) {
                    minIndex = i;
                } else {
                    if (counts[i] < counts[minIndex]) {
                        minIndex = i;
                    }
                }
                if (maxIndex == -1) {
                    maxIndex = i;
                } else {
                    if (counts[i] > counts[maxIndex]) {
                        maxIndex = i;
                    }
                }
            }
        }
        if (maxIndex == minIndex) {
            //所有出现的字符次数都一致
            if (counts[maxIndex] == word.length()) {
                //只有一种字符
                return true;
            }
            return counts[maxIndex] == 1;
        }
        //此时存在数量不一致
        int maxCount = 0;
        int minCount = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                if (counts[i] == counts[minIndex]) {
                    minCount++;
                } else if (counts[i] == counts[maxIndex]) {
                    maxCount++;
                } else {
                    return false;
                }
            }
        }
        //此时存在只存在两种不同数量的字符
        if (counts[maxIndex] - counts[minIndex] == 1 && maxCount == 1) {
            return true;
        }
        if (counts[minIndex] == 1 && minCount == 1) {
            return true;
        }
        return false;
    }
}
