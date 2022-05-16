package com.demo.algorithm.leetcode.contest.week293;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/5/16
 * @author chenglong
 * description : 移除字母异位词后的结果数组
 *
 * 给你一个下标从0开始的字符串words，其中words[i]由小写英文字符组成。
 * 在一步操作中，需要选出任一下标i ，从words中删除 words[i]。其中下标i需要同时满足下述两个条件：
 * 0 < i < words.length
 * words[i - 1]和words[i]是字母异位词 。
 * 只要可以选出满足条件的下标，就一直执行这个操作。
 * 在执行所有操作后，返回words。可以证明，按任意顺序为每步操作选择下标都会得到相同的结果。
 *
 * 字母异位词是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。例如，"dacb" 是 "abdc" 的一个字母异位词。
 *
 * 示例 1：
 * 输入：words = ["abba","baba","bbaa","cd","cd"]
 * 输出：["abba","cd"]
 * 解释：
 * 获取结果数组的方法之一是执行下述步骤：
 * - 由于 words[2] = "bbaa" 和 words[1] = "baba" 是字母异位词，选择下标 2 并删除 words[2] 。
 *   现在 words = ["abba","baba","cd","cd"] 。
 * - 由于 words[1] = "baba" 和 words[0] = "abba" 是字母异位词，选择下标 1 并删除 words[1] 。
 *   现在 words = ["abba","cd","cd"] 。
 * - 由于 words[2] = "cd" 和 words[1] = "cd" 是字母异位词，选择下标 2 并删除 words[2] 。
 *   现在 words = ["abba","cd"] 。
 * 无法再执行任何操作，所以 ["abba","cd"] 是最终答案。
 *
 * 示例 2：
 * 输入：words = ["a","b","c","d","e"]
 * 输出：["a","b","c","d","e"]
 * 解释：
 * words 中不存在互为字母异位词的两个相邻字符串，所以无需执行任何操作。
 *
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 */
public class RemoveAnagrams {

    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        int length = words.length;
        result.add(words[0]);
        for (int i = 1; i < length; i++) {
            String pre = result.get(result.size() - 1);
            if (!isSame(words[i], pre)) {
                result.add(words[i]);
            }
        }
        return result;
    }

    private boolean isSame(String word, String pre) {
        int length = word.length();
        if (pre.length() != length) {
            return false;
        }
        int[] counts = new int[26];
        for (int i = 0; i < length; i++) {
            counts[word.charAt(i) - 'a']++;
            counts[pre.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
