package com.demo.algorithm.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2024/1/12
 * @author chenglong
 * description : 统计出现过一次的公共字符串
 *
 * 给你两个字符串数组words1和words2，请你返回在两个字符串数组中都恰好出现一次的字符串的数目。
 *
 * 示例 1：
 * 输入：words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
 * 输出：2
 * 解释：
 * - "leetcode" 在两个数组中都恰好出现一次，计入答案。
 * - "amazing" 在两个数组中都恰好出现一次，计入答案。
 * - "is" 在两个数组中都出现过，但在 words1 中出现了 2 次，不计入答案。
 * - "as" 在 words1 中出现了一次，但是在 words2 中没有出现过，不计入答案。
 * 所以，有 2 个字符串在两个数组中都恰好出现了一次。
 *
 * 示例 2：
 * 输入：words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
 * 输出：0
 * 解释：没有字符串在两个数组中都恰好出现一次。
 *
 * 示例 3：
 * 输入：words1 = ["a","ab"], words2 = ["a","a","a","ab"]
 * 输出：1
 * 解释：唯一在两个数组中都出现一次的字符串是 "ab" 。
 *
 * 提示：
 * 1 <= words1.length, words2.length <= 1000
 * 1 <= words1[i].length, words2[j].length <= 30
 * words1[i] 和 words2[j] 都只包含小写英文字母。
 */
public class CountWords {

    public int countWords(String[] words1, String[] words2) {
        //1，统计字符出现的次数
        Map<String, Integer> dates1 = new HashMap<>();
        for (int i = 0; i < words1.length; i++) {
            String word = words1[i];
            if (dates1.containsKey(word)) {
                dates1.put(word, dates1.get(word) + 1);
            } else {
                dates1.put(word, 1);
            }
        }
        Map<String, Integer> dates2 = new HashMap<>();
        for (int i = 0; i < words2.length; i++) {
            String word = words2[i];
            if (dates2.containsKey(word)) {
                dates2.put(word, dates2.get(word) + 1);
            } else {
                dates2.put(word, 1);
            }
        }
        //2，找相同出现一次的单词
        int count = 0;
        for (String key : dates1.keySet()) {
            if (dates1.get(key) == 1) {
                if (dates2.containsKey(key) && dates2.get(key) == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
