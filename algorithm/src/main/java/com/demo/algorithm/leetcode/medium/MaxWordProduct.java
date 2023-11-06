package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * create on 11/17/21
 * @author chenglong
 * description : 最大单词长度乘积
 *
 * 给定一个字符串数组words，找到length(word[i]) * length(word[j])的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回0。
 *
 * 示例1:
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 *
 * 示例 2:
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 *
 * 示例 3:
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 *
 * 提示：
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i]仅包含小写字母
 */
public class MaxWordProduct {

    public int maxProduct(String[] words) {
        int max = 0;
        //1，对单词长度进行降序排序
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        //2，遍历查询
        int n = words.length;
        for (int i = 0; i < n - 1; i++) {
            //当前遍历条件下可能的最大值
            int checkMax = words[i].length() * words[i + 1].length();
            if (checkMax <= max) {
                //后续不存在比max更大的组合
                break;
            }
            for (int j = i + 1; j < n; j++) {
                int tem = words[i].length() * words[j].length();
                if (tem <= max) {
                    break;
                }
                if (hasSameWord(words[i], words[j])) {
                    max = tem;
                    break;
                }
            }
        }
        return max;
    }

    private boolean hasSameWord(String word, String compare) {
        boolean[] dates = new boolean[26];
        for (int i = 0; i < word.length(); i++) {
            dates[word.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < compare.length(); i++) {
            int index = compare.charAt(i) - 'a';
            if (dates[index]) {
                return false;
            }
        }
        return true;
    }
}
