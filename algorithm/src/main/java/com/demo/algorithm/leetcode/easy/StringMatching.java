package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/8/6
 * @author chenglong
 * description : 数组中的字符串匹配
 *
 * 给你一个字符串数组words，数组中的每个字符串都可以看作是一个单词。请你按任意顺序返回words中是其他单词的子字符串的所有单词。
 * 如果你可以删除 words[j]最左侧和/或最右侧的若干字符得到word[i]，那么字符串words[i]就是words[j]的一个子字符串。
 *
 * 示例 1：
 * 输入：words = ["mass","as","hero","superhero"]
 * 输出：["as","hero"]
 * 解释："as" 是 "mass" 的子字符串，"hero" 是 "superhero" 的子字符串。
 * ["hero","as"] 也是有效的答案。
 *
 * 示例 2：
 * 输入：words = ["leetcode","et","code"]
 * 输出：["et","code"]
 * 解释："et" 和 "code" 都是 "leetcode" 的子字符串。
 *
 * 示例 3：
 * 输入：words = ["blue","green","bu"]
 * 输出：[]
 *
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 30
 * words[i] 仅包含小写英文字母。
 * 题目数据 保证 每个 words[i] 都是独一无二的。
 */
public class StringMatching {

    public List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();
        int length = words.length;
        for (int i = 0; i < length; i++) {
            String find = words[i];
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    continue;
                }
                if (words[j].contains(find)) {
                    result.add(find);
                    break;
                }
            }
        }
        return result;
    }

}
