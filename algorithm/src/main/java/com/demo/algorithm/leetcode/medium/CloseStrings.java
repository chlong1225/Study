package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/11/30
 * @author chenglong
 * description : 确定两个字符串是否接近
 *
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串接近：
 * 操作1：交换任意两个现有字符。
 * 例如，abcde -> aecdb
 * 操作2：将一个现有字符的每次出现转换为另一个现有字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有a转化为b，而所有的b转换为a）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 * 给你两个字符串，word1和word2。如果word1和word2接近，就返回true；否则，返回false。
 *
 * 示例 1：
 * 输入：word1 = "abc", word2 = "bca"
 * 输出：true
 * 解释：2 次操作从 word1 获得 word2 。
 * 执行操作 1："abc" -> "acb"
 * 执行操作 1："acb" -> "bca"
 *
 * 示例 2：
 * 输入：word1 = "a", word2 = "aa"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 *
 * 示例 3：
 * 输入：word1 = "cabbba", word2 = "abbccc"
 * 输出：true
 * 解释：3 次操作从 word1 获得 word2 。
 * 执行操作 1："cabbba" -> "caabbb"
 * 执行操作 2："caabbb" -> "baaccc"
 * 执行操作 2："baaccc" -> "abbccc"
 *
 * 示例 4：
 * 输入：word1 = "cabbba", word2 = "aabbss"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 *
 * 提示：
 * 1 <= word1.length, word2.length <= 10^5
 * word1和word2仅包含小写英文字母
 */
public class CloseStrings {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] counts1 = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            counts1[word1.charAt(i) - 'a']++;
        }
        int[] counts2 = new int[26];
        for (int i = 0; i < word2.length(); i++) {
            counts2[word2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (counts1[i] > 0 && counts2[i] == 0) {
                return false;
            }
            if (counts1[i] == 0 && counts2[i] > 0) {
                return false;
            }
        }
        Arrays.sort(counts1);
        Arrays.sort(counts2);
        for (int i = 0; i < 26; i++) {
            if (counts1[i] != counts2[i]) {
                return false;
            }
        }
        return true;
    }
}
