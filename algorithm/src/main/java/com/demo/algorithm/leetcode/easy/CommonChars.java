package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 查找共用字符
 *
 * 给你一个字符串数组words，请你找出所有在words的每个字符串中都出现的共用字符（包括重复字符），并以数组形式返回。你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：words = ["bella","label","roller"]
 * 输出：["e","l","l"]
 *
 * 示例 2：
 * 输入：words = ["cool","lock","cook"]
 * 输出：["c","o"]
 *
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 由小写英文字母组成
 */
public class CommonChars {

    public List<String> commonChars(String[] words) {
        int[] allCounts = new int[26];
        for (int i = 0; i < words[0].length(); i++) {
            allCounts[words[0].charAt(i) - 'a']++;
        }
        for (int i = 1; i < words.length; i++) {
            int[] counts = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                counts[words[i].charAt(j) - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                allCounts[j] = Math.min(allCounts[j], counts[j]);
            }
        }
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (allCounts[i] > 0) {
                for (int j = 0; j < allCounts[i]; j++) {
                    char c = (char) ('a' + i);
                    dates.add("" + c);
                }
            }
        }
        return dates;
    }
}
