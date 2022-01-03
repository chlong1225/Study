package com.demo.algorithm.leetcode.hard;

import com.demo.algorithm.leetcode.entity.DictionaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/12/19.
 * description : 单词拆分II
 *
 * 给定一个非空字符串s和一个包含非空单词列表的字典wordDict，在字符串中增加空格来构建一个句子，
 * 使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 */
public class SplitWord2 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        //1，构建字典树，这默认都是小写字母
        DictionaryTree dictionaryTree = new DictionaryTree();
        int size = wordDict.size();
        //统计字母数量
        int[] counts = new int[26];
        for (int i = 0; i < size; i++) {
            String word = wordDict.get(i);
            insert(wordDict.get(i), dictionaryTree);
            int n = word.length();
            for (int j = 0; j < n; j++) {
                counts[word.charAt(j) - 'a']++;
            }
        }
        //2，单词能够拆分时，单词中的字母必须在字典中都能找到
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (counts[s.charAt(i) - 'a'] == 0) {
                return result;
            }
        }
        //3，深度优先遍历子串进行查找
        dfs(0, s, dictionaryTree, path, result);
        return result;
    }

    private void dfs(int start, String s, DictionaryTree root, List<String> path, List<String> result) {
        int length = s.length();
        if (start == length) {
            StringBuilder builder = new StringBuilder();
            int size = path.size();
            for (int i = 0; i < size; i++) {
                builder.append(path.get(i));
                if (i < size - 1) {
                    builder.append(" ");
                }
            }
            result.add(builder.toString());
        }
        DictionaryTree p = root;
        for (int i = start; i < length; i++) {
            int index = s.charAt(i) - 'a';
            p = p.childrens[index];
            if (p == null) {
                return;
            }
            if (p.isEnd) {
                path.add(s.substring(start, i + 1));
                dfs(i + 1, s, root, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    //在字典树中插入单词
    private void insert(String word, DictionaryTree dictionaryTree) {
        int length = word.length();
        if (length == 0) {
            return;
        }
        DictionaryTree p = dictionaryTree;
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (p.childrens[index] == null) {
                p.childrens[index] = new DictionaryTree();
            }
            p = p.childrens[index];
        }
        p.isEnd = true;
    }
}
