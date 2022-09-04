package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/8/22
 * @author chenglong
 * description : 检查单词是否为句中其他单词的前缀
 *
 * 给你一个字符串sentence作为句子并指定检索词为searchWord，其中句子由若干用单个空格分隔的单词组成。请你检查检索词searchWord是否为句子sentence中任意单词的前缀。
 * 如果searchWord是某一个单词的前缀，则返回句子sentence中该单词所对应的下标（下标从1开始）。如果searchWord是多个单词的前缀，则返回匹配的第一个单词的下标（最小下标）。
 * 如果 searchWord不是任何单词的前缀，则返回-1 。
 * 字符串s的前缀是s的任何前导连续子字符串。
 *
 * 示例 1：
 * 输入：sentence = "i love eating burger", searchWord = "burg"
 * 输出：4
 * 解释："burg" 是 "burger" 的前缀，而 "burger" 是句子中第 4 个单词。
 *
 * 示例 2：
 * 输入：sentence = "this problem is an easy problem", searchWord = "pro"
 * 输出：2
 * 解释："pro" 是 "problem" 的前缀，而 "problem" 是句子中第 2 个也是第 6 个单词，但是应该返回最小下标 2 。
 *
 * 示例 3：
 * 输入：sentence = "i am tired", searchWord = "you"
 * 输出：-1
 * 解释："you" 不是句子中任何单词的前缀。
 *
 * 提示：
 * 1 <= sentence.length <= 100
 * 1 <= searchWord.length <= 10
 * sentence 由小写英文字母和空格组成。
 * searchWord 由小写英文字母组成。
 */
public class PrefixOfWord {

    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (searchWord.length() > word.length()) {
                continue;
            }
            if (searchWord.equals(word.substring(0, searchWord.length()))) {
                return i + 1;
            }
        }
        return -1;
    }
}
