package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2021/11/17.
 * description : 单词接龙
 *
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 *
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 *
 * 示例 2：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *  
 * 提示：
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 */
public class WordSolitaire {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //1,检查endWord是否在wordList中
        int size = wordList.size();
        int endIndex = -1;
        for (int i = 0; i < size; i++) {
            if (endWord.equals(wordList.get(i))) {
                endIndex = i;
                break;
            }
        }
        //2,找不到endWord则肯定无法转换
        if (endIndex == -1) {
            return 0;
        }
        //3,使用hash记录单词的位置,避免遍历查找
        Map<String, Integer> wordIds = new HashMap<>();
        for (int i = 0; i < size; i++) {
            wordIds.put(wordList.get(i), i);
        }
        //4,双向广度遍历标记
        Map<String, Integer> startMarks = new HashMap<>();
        Map<String, Integer> endMarks = new HashMap<>();
        startMarks.put(beginWord, 0);
        endMarks.put(endWord, 0);
        List<String> startWords = new ArrayList<>();
        startWords.add(beginWord);
        List<String> endWords = new ArrayList<>();
        endWords.add(endWord);
        while ((!startWords.isEmpty()) && (!endWords.isEmpty())) {
            if (startWords.size() <= endWords.size()) {
                //从左边开始转换
                int step = updateWords(startWords, startMarks, wordIds, endMarks);
                if (step != -1) {
                    return step;
                }
            } else {
                //从右边开始转换
                int step = updateWords(endWords, endMarks, wordIds, startMarks);
                if (step != -1) {
                    return step;
                }
            }
        }
        return 0;
    }

    private static int updateWords(List<String> words, Map<String, Integer> marks, Map<String, Integer> wordIds, Map<String, Integer> findMarks) {
        List<String> tem = new ArrayList<>();
        int size = words.size();
        for (int i = 0; i < size; i++) {
            String word = words.get(i);
            int step = marks.get(word);
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                for (char k = 'a'; k <= 'z'; k++) {
                    if (k != c) {
                        StringBuilder newBuild = new StringBuilder();
                        newBuild.append(word.substring(0, j));
                        newBuild.append(k);
                        newBuild.append(word.substring(j + 1));
                        String newWord = newBuild.toString();
                        //5.1,转换后的单词在单词列表中找不到,则转换无效
                        if (wordIds.get(newWord) == null) {
                            continue;
                        }
                        //5.2,新的单词之前是否被转换标记过,已经转换过则转换无效,否则记录,并下次转换
                        if (marks.get(newWord) != null) {
                            continue;
                        }
                        //5.3,新单词是否在从后转换中被标记
                        if (findMarks.get(newWord) != null) {
                            return marks.get(word) + findMarks.get(newWord) + 2;
                        }
                        marks.put(newWord, step + 1);
                        tem.add(newWord);
                    }
                }
            }
        }
        words.clear();
        words.addAll(tem);
        return -1;
    }

}
