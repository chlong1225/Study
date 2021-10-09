package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 10/9/21
 * @author chenglong
 * description : 串联所有单词的子串
 *
 * 给定一个字符串s和一些长度相同的单词 words 。找出s中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 *
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *  
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 */
public class FindAllSubstring {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        int length = s.length();
        int wordLength = words[0].length();
        int totalWordLength = wordLength * words.length;
        if (totalWordLength > length) {
            return result;
        }
        for (int i = 0; i <= length - totalWordLength; i++) {
            boolean compare = compare(s, i, wordLength, words);
            if (compare) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean compare(String s, int index, int wordLength, String[] words) {
        int length = words.length;
        boolean[] hasCompare = new boolean[length];
        int count = 0;
        while (count < length) {
            String compare = s.substring(index, index + wordLength);
            boolean isCompare = false;
            for (int i = 0; i < length; i++) {
                if (!hasCompare[i]) {
                    if (compare.equals(words[i])) {
                        hasCompare[i] = true;
                        isCompare = true;
                        break;
                    }
                }
            }
            if (!isCompare) {
                return false;
            }
            index += wordLength;
            count++;
        }
        return true;
    }
}
