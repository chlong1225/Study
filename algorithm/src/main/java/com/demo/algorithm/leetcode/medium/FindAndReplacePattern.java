package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/6/12.
 * description : 查找和替换模式
 *
 * 你有一个单词列表words和一个模式pattern，你想知道words中的哪些单词与模式匹配。
 * 如果存在字母的排列p，使得将模式中的每个字母x替换为p(x)之后，我们就得到了所需的单词，那么单词与模式是匹配的。
 * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
 * 返回 words中与给定模式匹配的单词列表。
 * 你可以按任何顺序返回答案。
 *
 * 示例：
 * 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * 输出：["mee","aqq"]
 * 解释：
 * "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
 * "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
 * 因为 a 和 b 映射到同一个字母。
 *
 * 提示：
 * 1 <= words.length <= 50
 * 1 <= pattern.length = words[i].length<= 20
 */
public class FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        int length = words.length;
        for (int i = 0; i < length; i++) {
            if (checkReplace(words[i], pattern)) {
                result.add(words[i]);
            }
        }
        return result;
    }

    //判断word是否可以转换为pattern
    private boolean checkReplace(String word, String pattern) {
        int length = word.length();
        if (pattern.length() != length) {
            return false;
        }
        int[] index1 = new int[128];
        int[] index2 = new int[128];
        for (int i = 0; i < length; i++) {
            char old = word.charAt(i);
            char news = pattern.charAt(i);
            if (index1[old] == 0) {
                //没有old~news的对照关系
                if (index2[news] != 0) {
                    return false;
                }
                index1[old] = news;
                index2[news] = old;
            } else {
                if (index1[old] != news) {
                    return false;
                }
                //此时有old~news的对照关系
                if (index2[news] == 0 || index2[news] != old) {
                    return false;
                }
            }
        }
        return true;
    }
}
