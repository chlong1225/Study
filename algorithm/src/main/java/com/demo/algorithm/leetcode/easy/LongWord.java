package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chl on 2022/3/18.
 * description : 词典中最长的单词
 *
 * 给出一个字符串数组words组成的一本英语词典。返回words中最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 *
 * 示例 1：
 * 输入：words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
 *
 * 示例 2：
 * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
 *
 * 提示：
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * 所有输入的字符串words[i]都只包含小写字母。
 */
public class LongWord {

    public String longestWord(String[] words) {
        /**
         * 单词长度远远小于长度，可以使用桶分类
         */
        List<List<String>> dates = new ArrayList<>(31);
        for (int i = 0; i < 31; i++) {
            dates.add(new ArrayList<>());
        }
        //1，根据数据长度进行分类
        int length = words.length;
        for (int i = 0; i < length; i++) {
            String tem = words[i];
            dates.get(tem.length()).add(tem);
        }
        //2，对每个长度的单词进行排序
        for (int i = 0; i < 31; i++) {
            if (dates.get(i).size() > 1) {
                Collections.sort(dates.get(i));
            }
        }
        //3，查找满足条件的最长单词
        if (dates.get(1).size() == 0) {
            return "";
        }
        List<String> result = new ArrayList<>();
        result.addAll(dates.get(1));
        for (int i = 2; i < 31; i++) {
            List<String> items = dates.get(i);
            if (items.size() == 0) {
                break;
            }
            List<String> next = new ArrayList<>();
            for (int j = 0; j < items.size(); j++) {
                if (isContainer(items.get(j), result)) {
                    next.add(items.get(j));
                }
            }
            if (next.size() == 0) {
                break;
            }
            result.clear();
            result.addAll(next);
        }
        return result.get(0);
    }

    private boolean isContainer(String compare, List<String> dates) {
        compare = compare.substring(0, compare.length() - 1);
        for (int i = 0; i < dates.size(); i++) {
            if (compare.equals(dates.get(i))) {
                return true;
            }
        }
        return false;

    }
}
