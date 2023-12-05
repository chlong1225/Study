package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/5
 * @author chenglong
 * description : 字符串中的单词数
 *
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 *
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 * 示例:
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作1个单词。
 */
public class CountSegments {

    public int countSegments(String s) {
        int count = 0;
        //查找第一个不是空格的位置，即单词的起始位置
        int firstWord = -1;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ' ') {
                if (firstWord != -1) {
                    firstWord = -1;
                    count++;
                }
            } else {
                if (firstWord == -1) {
                    firstWord = i;
                }
            }
        }
        if (firstWord != -1) {
            count++;
        }
        return count;
    }
}
