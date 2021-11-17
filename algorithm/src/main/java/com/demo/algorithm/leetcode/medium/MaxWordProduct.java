package com.demo.algorithm.leetcode.medium;

/**
 * create on 11/17/21
 * @author chenglong
 * description : 最大单词长度乘积
 *
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 *
 * 示例 1:
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
 * words[i] 仅包含小写字母
 */
public class MaxWordProduct {

    public int maxProduct(String[] words) {
        //1，将单词转换为int值便于使用位运算进行比较
        int length = words.length;
        int[] datas = new int[length];
        for (int i = 0; i < length; i++) {
            datas[i] = strToInt(words[i]);
        }
        //2,遍历比较并乘积
        int max = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if ((datas[i] & datas[j]) == 0) {
                    //i，j位置的两个字符串没有重复的
                    int product = words[i].length() * words[j].length();
                    if (product > max) {
                        max = product;
                    }
                }
            }
        }
        return max;
    }

    private int strToInt(String word) {
        //1，统计每个字符是否出现，counts最终只包含0，1
        int[] counts = new int[26];
        int length = word.length();
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (counts[index] == 0) {
                counts[index] = 1;
            }
        }
        //2，转换为数字
        int product = 2;
        int result = counts[0];
        for (int i = 1; i < 26; i++) {
            result += counts[i] * product;
            product *= 2;
        }
        return result;
    }
}
