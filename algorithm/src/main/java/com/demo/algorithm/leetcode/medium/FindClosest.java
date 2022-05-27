package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/5/27
 * @author chenglong
 * description : 面试题17.11. 单词距离
 *
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 *
 * 示例：
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 *
 * 提示：
 * words.length <= 100000
 */
public class FindClosest {

    public int findClosest(String[] words, String word1, String word2) {
        if (words == null || words.length < 2) {
            return 0;
        }
        Map<String, Integer> marks = new HashMap<>();
        int length = words.length;
        int min = Integer.MAX_VALUE;
        marks.put(words[0], 0);
        for (int i = 1; i < length; i++) {
            String cur = words[i];
            if (word1.equals(cur)) {
                if (marks.containsKey(word2)) {
                    int space = i - marks.get(word2);
                    if (space == 1) {
                        return 1;
                    }
                    if (space < min) {
                        min = space;
                    }
                }
            } else if (word2.equals(cur)) {
                if (marks.containsKey(word1)) {
                    int space = i - marks.get(word1);
                    if (space == 1) {
                        return 1;
                    }
                    if (space < min) {
                        min = space;
                    }
                }
            }
            marks.put(cur, i);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
