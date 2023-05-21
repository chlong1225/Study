package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * create on 2023/4/27
 * @author chenglong
 * description : 最长字符串链
 *
 * 给出一个单词数组words，其中每个单词都由小写英文字母组成。
 * 如果我们可以不改变其他字符的顺序，在wordA的任何地方添加恰好一个字母使其变成wordB，那么我们认为wordA是wordB的前身。
 * 例如，"abc"是"abac"的前身，而"cba"不是"bcad"的前身
 * 词链是单词[word_1, word_2, ..., word_k]组成的序列，k >= 1，其中word1是word2的前身，word2是word3的前身，依此类推。一个单词通常是k==1的单词链。
 * 从给定单词列表words中选择单词组成词链，返回词链的最长可能长度 。
 *
 * 示例 1：
 * 输入：words = ["a","b","ba","bca","bda","bdca"]
 * 输出：4
 * 解释：最长单词链之一为 ["a","ba","bda","bdca"]
 *
 * 示例 2:
 * 输入：words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * 输出：5
 * 解释：所有的单词都可以放入单词链 ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
 *
 * 示例3:
 * 输入：words = ["abcd","dbqca"]
 * 输出：1
 * 解释：字链["abcd"]是最长的字链之一。
 * ["abcd"，"dbqca"]不是一个有效的单词链，因为字母的顺序被改变了。
 *
 * 提示：
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i]仅由小写英文字母组成。
 */
public class LongestStrChain {

    public int longestStrChain(String[] words) {
        //1，对数据进行分组并去重
        List<Set<String>> dates = new ArrayList<>(17);
        for (int i = 0; i < 17; i++) {
            dates.add(new HashSet<>());
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            dates.get(word.length()).add(word);
        }
        Map<String, Integer> marks = new HashMap<>();
        int maxCount = 1;
        //2，获取起始搜索入口并初始化
        List<String> curs = new ArrayList<>();
        for (int i = 0; i < dates.size() - 1; i++) {
            if (i == 0) {
                if (dates.get(i).size() > 0) {
                    curs.addAll(dates.get(i));
                }
            } else {
                if (dates.get(i).size() > 0 && dates.get(i - 1).size() == 0) {
                    curs.addAll(dates.get(i));
                }
            }
        }
        for (int i = 0; i < curs.size(); i++) {
            marks.put(curs.get(i), 1);
        }
        //3，广度遍历
        List<String> nexts = new ArrayList<>();
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                String cur = curs.get(i);
                //需要查找的长度
                int findLength = cur.length() + 1;
                if (findLength < dates.size() && dates.get(findLength).size() > 0) {
                    List<String> finds = new ArrayList<>(dates.get(findLength));
                    for (int j = 0; j < finds.size(); j++) {
                        String find = finds.get(j);
                        int preCount = marks.get(cur);
                        if (marks.containsKey(find)) {
                            if (marks.get(find) >= preCount + 1) {
                                continue;
                            }
                            if (checkBefore(cur, find)) {
                                marks.put(find, preCount + 1);
                                if (preCount + 1 > maxCount) {
                                    maxCount = preCount + 1;
                                }
                            }
                        } else {
                            if (checkBefore(cur, find)) {
                                nexts.add(find);
                                marks.put(find, preCount + 1);
                                if (preCount + 1 > maxCount) {
                                    maxCount = preCount + 1;
                                }
                            } else {
                                nexts.add(find);
                                marks.put(find, 1);
                            }
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return maxCount;
    }

    //判断cur是否为find的前身
    private boolean checkBefore(String cur, String find) {
        int length1 = cur.length();
        int length2 = find.length();
        int index1 = 0;
        int index2 = 0;
        while (index1 < length1 && index2 < length2) {
            if (cur.charAt(index1) == find.charAt(index2)) {
                index1++;
                index2++;
            } else {
                if (index1 == index2) {
                    index2++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
