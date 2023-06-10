package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * Created by chl on 2023/6/10.
 * description :  比较字符串最小字母出现频次
 *
 * 定义一个函数f(s)，统计s中（按字典序比较）最小字母的出现频次，其中s是一个非空字符串。
 * 例如，若s = "dcce"，那么f(s) = 2，因为字典序最小字母是"c"，它出现了2次。
 * 现在，给你两个字符串数组待查表queries和词汇表words 。对于每次查询queries[i] ，需统计words中满足f(queries[i])< f(W)的词的数目，
 * W表示词汇表words中的每个词。
 * 请你返回一个整数数组answer作为答案，其中每个answer[i]是第i次查询的结果。
 *
 * 示例 1：
 * 输入：queries = ["cbd"], words = ["zaaaz"]
 * 输出：[1]
 * 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
 *
 * 示例 2：
 * 输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * 输出：[1,2]
 * 解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
 *
 * 提示：
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j]、words[i][j] 都由小写英文字母组成
 */
public class NumSmallerByFrequency {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        //1，统计works中每一个单词的f(s)大小
        int length = words.length;
        int[] dates = new int[length];
        for (int i = 0; i < length; i++) {
            dates[i] = getCount(words[i]);
        }
        //2，排序
        Arrays.sort(dates);
        int n = queries.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            //用于比对的f(s)
            int compare = getCount(queries[i]);
            int index = getBigIndex(compare, dates);
            if (index == -1) {
                result[i] = 0;
            } else {
                result[i] = length - index;
            }
        }
        return result;
    }

    private int getBigIndex(int compare, int[] dates) {
        int start = 0;
        int end = dates.length - 1;
        if (compare < dates[0]) {
            return 0;
        }
        if (compare >= dates[end]) {
            return -1;
        }
        while (start < end) {
            int middle = (end - start) / 2 + start;
            if (dates[middle] <= compare) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }
        return start;
    }

    private int getCount(String word) {
        int[] counts = new int[26];
        for (int i = 0; i < word.length(); i++) {
            counts[word.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                return counts[i];
            }
        }
        return 0;
    }
}
