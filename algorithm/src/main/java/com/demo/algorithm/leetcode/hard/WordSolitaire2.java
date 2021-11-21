package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2021/11/17.
 * description : 单词接龙II
 *
 * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
 * 每对相邻的单词之间仅有单个字母不同。
 * 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 *
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * 解释：存在 2 种最短的转换序列：
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 *
 * 示例 2：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：[]
 * 解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
 *  
 * 提示：
 * 1 <= beginWord.length <= 7
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有单词 互不相同
 */
public class WordSolitaire2 {

    private Map<String, Integer> wordIds = new HashMap<>();
    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        result.clear();
        wordIds.clear();
        int endIndex = -1;
        for (int i = 0; i < wordList.size(); i++) {
            wordIds.put(wordList.get(i), i);
            if (endWord.equals(wordList.get(i))) {
                endIndex = i;
            }
        }
        if (endIndex == -1) {
            return result;
        }
        Map<String, List<String>> startMarks = new HashMap<>();
        Map<String, List<String>> endMarks = new HashMap<>();
        List<String> startWords = new ArrayList<>();
        List<String> endWords = new ArrayList<>();
        List<String> data1 = new ArrayList<>();
        data1.add(beginWord);
        startMarks.put(beginWord, data1);
        startWords.add(beginWord);
        List<String> data2 = new ArrayList<>();
        data2.add(endWord);
        endMarks.put(endWord, data2);
        endWords.add(endWord);
        while (!startWords.isEmpty() && !endWords.isEmpty()) {
            if (startWords.size() <= endWords.size()) {
                update(startWords, startMarks, endMarks, true);
            } else {
                update(endWords, endMarks, startMarks, false);
            }
            if (result.size() > 0) {
                return result;
            }
        }
        return result;
    }

    private void update(List<String> words, Map<String, List<String>> marks, Map<String, List<String>> findMarks, boolean isStart) {
        List<String> tem = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                for (char k = 'a'; k <= 'z'; k++) {
                    if (c == k) {
                        continue;
                    }
                    StringBuilder newBuild = new StringBuilder();
                    newBuild.append(word.substring(0, j));
                    newBuild.append(k);
                    newBuild.append(word.substring(j + 1));
                    String newWord = newBuild.toString();
                    if (wordIds.get(newWord) == null) {
                        continue;
                    }
                    if (findMarks.get(newWord) != null) {
                        if (isStart) {
                            List<List<String>> data = buildResult(word.length(), marks.get(word), findMarks.get(newWord));
                            result.addAll(data);
                        } else {
                            List<List<String>> data = buildResult(word.length(),findMarks.get(newWord), marks.get(word));
                            result.addAll(data);
                        }
                    } else {
                        if (marks.get(newWord) == null) {
                            List<String> data = new ArrayList<>();
                            for (int l = 0; l < marks.get(word).size(); l++) {
                                data.add(marks.get(word).get(l) + newWord);
                            }
                            marks.put(newWord, data);
                            tem.add(newWord);
                        } else {
                            List<String> compares = marks.get(newWord);
                            int newLength = marks.get(word).get(0).length() + newWord.length();
                            if (newLength == compares.get(0).length()) {
                                for (int l = 0; l < marks.get(word).size(); l++) {
                                    compares.add(marks.get(word).get(l) + newWord);
                                }
                                marks.put(newWord, compares);
                                if (!tem.contains(newWord)) {
                                    tem.add(newWord);
                                }
                            }
                        }
                    }
                }
            }
        }
        words.clear();
        words.addAll(tem);
    }

    private List<List<String>> buildResult(int size, List<String> starts, List<String> ends) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < starts.size(); i++) {
            String start = starts.get(i);
            for (int j = 0; j < ends.size(); j++) {
                String end = ends.get(j);
                List<String> data = new ArrayList<>();
                int statCount = start.length() / size;
                int endCount = end.length() / size;
                for (int k = 0; k < statCount; k++) {
                    data.add(start.substring(size * k, size * (k + 1)));
                }
                for (int k = endCount - 1; k >= 0; k--) {
                    data.add(end.substring(k * size, (k + 1) * size));
                }
                result.add(data);
            }
        }
        return result;
    }
}
