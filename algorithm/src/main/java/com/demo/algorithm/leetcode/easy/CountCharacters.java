package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 拼写单词
 *
 * 给你一份『词汇表』（字符串数组）words和一张『字母表』（字符串）chars。
 * 假如你可以用chars中的『字母』（字符）拼写出words中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * 注意：每次拼写（指拼写词汇表中的一个单词）时，chars中的每个字母都只能用一次。
 * 返回词汇表words中你掌握的所有单词的长度之和。
 *
 * 示例 1：
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 *
 * 示例 2：
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 *
 * 提示：
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * 所有字符串中都仅包含小写英文字母
 */
public class CountCharacters {

    public int countCharacters(String[] words, String chars) {
        int[] compare = new int[26];
        for (int i = 0; i < chars.length(); i++) {
            compare[chars.charAt(i) - 'a']++;
        }
        int sum = 0;
        for (int i = 0; i < words.length; i++) {
            if (checkWord(words[i], compare)) {
                sum += words[i].length();
            }
        }
        return sum;
    }

    private boolean checkWord(String word, int[] compare) {
        int[] counts = new int[26];
        for (int i = 0; i < word.length(); i++) {
            counts[word.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (counts[i] > compare[i]) {
                return false;
            }
        }
        return true;
    }
}
