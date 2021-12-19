package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chl on 2021/12/19.
 * description : 单词拆分
 *
 * 给你一个字符串s和一个字符串列表wordDict作为字典，判定s是否可以由空格拆分为一个或多个在字典中出现的单词。
 * 说明：拆分时可以重复使用字典中的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *  
 * 提示：
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 */
public class SplitWord {

    private Map<Integer, Boolean> marks = new HashMap<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        marks.clear();
        return dfs(0, s, wordDict);
    }

    private boolean dfs(int index, String s, List<String> wordDict) {
        int length = s.length();
        if (index == length) {
            return true;
        }
        if (marks.get(index) != null) {
            return marks.get(index);
        }
        int size = wordDict.size();
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (result) {
                break;
            }
            String word = wordDict.get(i);
            if (index + word.length() > length) {
                continue;
            }
            if (word.equals(s.substring(index, index + word.length()))) {
                result = dfs(index + word.length(), s, wordDict);
            }
        }
        marks.put(index, result);
        return result;
    }
}
