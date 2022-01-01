package com.demo.algorithm.leetcode.hard;

import com.demo.algorithm.leetcode.entity.DictionaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chl on 2021/12/30.
 * description : 连接词
 *
 * 给你一个不含重复单词的字符串数组words，请你找出并返回words中的所有连接词 。
 * 连接词定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
 *
 * 示例 1：
 * 输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 解释："catsdogcats" 由 "cats", "dog" 和 "cats" 组成;
 *      "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成;
 *      "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
 *
 * 示例 2：
 * 输入：words = ["cat","dog","catdog"]
 * 输出：["catdog"]
 *
 * 提示：
 * 1 <= words.length <= 104
 * 0 <= words[i].length <= 1000
 * words[i] 仅由小写字母组成
 * 0 <= sum(words[i].length) <= 105
 */
public class ConjunctionWord {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        int length = words.length;
        List<String> result = new ArrayList<>();
        if (length == 1) {
            return result;
        }
        //1，将单词按照长度进行排序
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        //2，构建字典树
        DictionaryTree tree = new DictionaryTree();
        //最小长度的单词直接构建字典树,肯定无法拆分。同时能够拆分的单词长度最小值为2*minLength
        int minLength = words[0].length();
        if (minLength == 0) {
            //""不参与连接组合
            minLength = words[1].length();
        }
        for (int i = 0; i < length; i++) {
            String word = words[i];
            if (word.length() == 0) {
                continue;
            }
            if (word.length() < 2 * minLength) {
                inserTree(word, tree);
                continue;
            }
            boolean isFind = find(0, word, tree);
            if (isFind) {
                result.add(word);
            } else {
                inserTree(word, tree);
            }
        }
        return result;
    }

    //在字典树中循环查找单词word
    private boolean find(int start, String word, DictionaryTree tree) {
        int length = word.length();
        if (start == length) {
            return true;
        }
        DictionaryTree p = tree;
        for (int i = start; i < length; i++) {
            int index = word.charAt(i) - 'a';
            p = p.childrens[index];
            if (p == null) {
                return false;
            }
            if (p.isEnd) {
                if (find(i + 1, word, tree)) {
                    return true;
                }
            }
        }
        return false;
    }

    //字典树中插入单词
    private void inserTree(String word, DictionaryTree tree) {
        DictionaryTree p = tree;
        int length = word.length();
        if (length == 0) {
            return;
        }
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
