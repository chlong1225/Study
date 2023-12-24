package com.demo.algorithm.leetcode.easy;

import java.util.List;

/**
 * create on 2023/12/20
 * @author chenglong
 * description : 判别首字母缩略词
 *
 * 给你一个字符串数组words和一个字符串s，请你判断s是不是words的首字母缩略词。
 * 如果可以按顺序串联words中每个字符串的第一个字符形成字符串s，则认为s是words的首字母缩略词。
 * 例如，"ab"可以由 ["apple", "banana"] 形成，但是无法从 ["bear", "aardvark"] 形成。
 * 如果s是words的首字母缩略词，返回true；否则返回false。
 *
 * 示例 1：
 * 输入：words = ["alice","bob","charlie"], s = "abc"
 * 输出：true
 * 解释：words 中 "alice"、"bob" 和 "charlie" 的第一个字符分别是 'a'、'b' 和 'c'。因此，s = "abc" 是首字母缩略词。
 *
 * 示例 2：
 * 输入：words = ["an","apple"], s = "a"
 * 输出：false
 * 解释：words 中 "an" 和 "apple" 的第一个字符分别是 'a' 和 'a'。
 * 串联这些字符形成的首字母缩略词是 "aa" 。
 * 因此，s = "a" 不是首字母缩略词。
 *
 * 示例 3：
 * 输入：words = ["never","gonna","give","up","on","you"], s = "ngguoy"
 * 输出：true
 * 解释：串联数组 words 中每个字符串的第一个字符，得到字符串 "ngguoy" 。
 * 因此，s = "ngguoy" 是首字母缩略词。
 *
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 10
 * 1 <= s.length <= 100
 * words[i] 和 s 由小写英文字母组成
 */
public class Acronym {

    public boolean isAcronym(List<String> words, String s) {
        if (words.size() != s.length()) {
            return false;
        }
        int n = words.size();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != words.get(i).charAt(0)) {
                return false;
            }
        }
        return true;
    }
}
