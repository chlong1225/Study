package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2022/1/30.
 * description : 两句话中的不常见单词
 *
 * 句子是一串由空格分隔的单词。每个单词仅由小写字母组成。
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的 。
 * 给你两个句子s1和s2，返回所有不常用单词的列表。返回列表中单词可以按任意顺序组织。
 *
 * 示例 1：
 * 输入：s1 = "this apple is sweet", s2 = "this apple is sour"
 * 输出：["sweet","sour"]
 *
 * 示例 2：
 * 输入：s1 = "apple apple", s2 = "banana"
 * 输出：["banana"]
 *
 * 提示：
 * 1 <= s1.length, s2.length <= 200
 * s1 和 s2 由小写英文字母和空格组成
 * s1 和 s2 都不含前导或尾随空格
 * s1 和 s2 中的所有单词间均由单个空格分隔
 */


public class UncommonWord {

    public String[] uncommonFromSentences(String s1, String s2) {
        String[] data1 = s1.split(" ");
        String[] data2 = s2.split(" ");
        Map<String, Integer> marks = new HashMap<>();
        int length = data1.length;
        for (int i = 0; i < length; i++) {
            if (marks.get(data1[i]) == null) {
                marks.put(data1[i], 1);
            } else {
                marks.put(data1[i], marks.get(data1[i]) + 1);
            }
        }
        length = data2.length;
        for (int i = 0; i < length; i++) {
            if (marks.get(data2[i]) == null) {
                marks.put(data2[i], 1);
            } else {
                marks.put(data2[i], marks.get(data2[i]) + 1);
            }
        }
        List<String> items = new ArrayList<>();
        for (String key : marks.keySet()) {
            if (marks.get(key) == 1) {
                items.add(key);
            }
        }
        length = items.size();
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = items.get(i);
        }
        return result;
    }
}
