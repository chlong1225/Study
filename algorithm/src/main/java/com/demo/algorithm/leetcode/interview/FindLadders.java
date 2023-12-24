package com.demo.algorithm.leetcode.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * create on 2023/12/20
 * @author chenglong
 * description : 面试题 17.22. 单词转换
 *
 * 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词，但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
 * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
 *
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出:
 * ["hit","hot","dot","lot","log","cog"]
 *
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: []
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 */
public class FindLadders {

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0 || beginWord == null || endWord == null || beginWord.length() != endWord.length()) {
            return new ArrayList<>();
        }
        List<String> answer = new ArrayList<>();
        if (beginWord.equals(endWord)) {
            answer.add(beginWord);
            return answer;
        }
        Set<String> marks = new HashSet<>();
        boolean hasFind = false;
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i) != null && wordList.get(i).length() == beginWord.length()) {
                //过滤掉无效的单词
                String word = wordList.get(i);
                if (!marks.contains(word)) {
                    if (endWord.equals(word)) {
                        hasFind = true;
                    }
                    dates.add(word);
                    marks.add(word);
                }
            }
        }
        if (!hasFind) {
            return answer;

        }
        marks.clear();
        hasFind = false;
        List<String> curs = new ArrayList<>();
        List<String> nexts = new ArrayList<>();
        curs.add(beginWord);
        marks.add(beginWord);
        //记录当前单词被转换来的单词
        Map<String, String> repaces = new HashMap<>();
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                for (int j = 0; j < dates.size(); j++) {
                    if (compareWord(curs.get(i), dates.get(j))) {
                        //可以从：curs.get(i) ——> dates.get(j)
                        if (!marks.contains(dates.get(j))) {
                            marks.add(dates.get(j));
                            nexts.add(dates.get(j));
                            repaces.put(dates.get(j), curs.get(i));
                            if (dates.get(j).equals(endWord)) {
                                hasFind = true;
                                break;
                            }
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        if (hasFind) {
            answer.add(endWord);
            String cur = endWord;
            while (!cur.equals(beginWord)) {
                cur = repaces.get(cur);
                answer.add(cur);
            }
            Collections.reverse(answer);
        }
        return answer;
    }

    private boolean compareWord(String compare, String word) {
        int diffCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != compare.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
            }
        }
        return diffCount == 1;
    }
}
