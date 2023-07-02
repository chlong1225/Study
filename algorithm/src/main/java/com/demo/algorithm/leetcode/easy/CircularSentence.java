package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/6/30
 * @author chenglong
 * description : 回环句
 *
 * 句子是由单个空格分隔的一组单词，且不含前导或尾随空格。
 * 例如，"Hello World"、"HELLO"、"hello world hello world" 都是符合要求的句子。
 * 单词 仅 由大写和小写英文字母组成。且大写和小写字母会视作不同字符。
 * 如果句子满足下述全部条件，则认为它是一个回环句 ：
 * 单词的最后一个字符和下一个单词的第一个字符相等。
 * 最后一个单词的最后一个字符和第一个单词的第一个字符相等。
 * 例如，"leetcode exercises sound delightful"、"eetcode"、"leetcode eats soul" 都是回环句。
 * 然而，"Leetcode is cool"、"happy Leetcode"、"Leetcode" 和 "I like Leetcode" 都不是回环句。
 *
 * 给你一个字符串sentence，请你判断它是不是一个回环句。如果是返回true；否则返回false。
 *
 * 示例 1：
 * 输入：sentence = "leetcode exercises sound delightful"
 * 输出：true
 * 解释：句子中的单词是 ["leetcode", "exercises", "sound", "delightful"] 。
 * - leetcode 的最后一个字符和 exercises 的第一个字符相等。
 * - exercises 的最后一个字符和 sound 的第一个字符相等。
 * - sound 的最后一个字符和 delightful 的第一个字符相等。
 * - delightful 的最后一个字符和 leetcode 的第一个字符相等。
 * 这个句子是回环句。
 *
 * 示例 2：
 * 输入：sentence = "eetcode"
 * 输出：true
 * 解释：句子中的单词是 ["eetcode"] 。
 * - eetcode 的最后一个字符和 eetcode 的第一个字符相等。
 * 这个句子是回环句。
 *
 * 示例 3：
 * 输入：sentence = "Leetcode is cool"
 * 输出：false
 * 解释：句子中的单词是 ["Leetcode", "is", "cool"] 。
 * - Leetcode的最后一个字符和is的第一个字符不相等。
 * 这个句子不是回环句。
 *
 * 提示：
 * 1 <= sentence.length <= 500
 * sentence 仅由大小写英文字母和空格组成
 * sentence 中的单词由单个空格进行分隔
 * 不含任何前导或尾随空格
 */
public class CircularSentence {

    public boolean isCircularSentence(String sentence) {
        String[] dates = sentence.split(" ");
        for (int i = 0; i < dates.length - 1; i++) {
            String cur = dates[i];
            String next = dates[i + 1];
            if (cur.charAt(cur.length() - 1) != next.charAt(0)) {
                return false;
            }
        }
        String last = dates[dates.length - 1];
        return dates[0].charAt(0) == last.charAt(last.length() - 1);
    }
}
