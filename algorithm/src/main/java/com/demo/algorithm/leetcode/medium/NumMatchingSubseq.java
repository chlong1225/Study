package com.demo.algorithm.leetcode.medium;


import java.util.Arrays;

/**
 * create on 2022/11/17
 * @author chenglong
 * description : 匹配子序列的单词数
 *
 * 给定字符串s和字符串数组words, 返回words[i]中是s的子序列的单词个数。
 * 字符串的子序列是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 * 例如， “ace” 是 “abcde” 的子序列。
 *
 * 示例 1:
 * 输入: s = "abcde", words = ["a","bb","acd","ace"]
 * 输出: 3
 * 解释: 有三个是s的子序列的单词: "a", "acd", "ace"。
 *
 * 示例 2:
 * 输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * 输出: 2
 *
 * 提示:
 * 1 <= s.length <= 5 * 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * words[i]和s都只由小写字母组成。
 */
public class NumMatchingSubseq {

    public int numMatchingSubseq(String s, String[] words) {
        int length = s.length();
        //记录当前位置下一个字母的index
        int[][] marks = new int[length][26];
        for (int i = 0; i < length; i++) {
            Arrays.fill(marks[i], -1);
        }
        int[] nextIndex = new int[26];
        Arrays.fill(nextIndex, -1);
        for (int i = length - 1; i >= 0; i--) {
            int cur = s.charAt(i) - 'a';
            marks[i][cur] = i;
            for (int j = 0; j < 26; j++) {
                if (j == cur || nextIndex[j] == -1) {
                    continue;
                }
                marks[i][j] = nextIndex[j];
            }
            nextIndex[cur] = i;
        }
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (find(words[i], nextIndex, marks)) {
                count++;
            }
        }
        return count;
    }

    private boolean find(String word, int[] firstIndex, int[][] marks) {
        if (firstIndex[word.charAt(0) - 'a'] == -1) {
            return false;
        }
        if (word.length() == 1) {
            return true;
        }
        int start = firstIndex[word.charAt(0) - 'a'];
        int index = 1;
        while (start < marks.length) {
            //需要找到的字符
            int find = word.charAt(index) - 'a';
            if (marks[start][find] == -1) {
                return false;
            }
            int pre = start;
            start = marks[pre][find];
            if (start == pre) {
                start = marks[pre + 1][find];
            }
            if (start == -1) {
                return false;
            }
            index++;
            if (index == word.length()) {
                return true;
            }
        }
        return false;
    }
}
