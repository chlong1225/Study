package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/3/2
 * @author chenglong
 * description : 单词规律
 *
 * 给定一种规律pattern和一个字符串s，判断s是否遵循相同的规律。
 * 这里的遵循指完全匹配，例如，pattern里的每个字母和字符串s中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 * 输入: pattern = "abba", s = "dog cat cat dog"
 * 输出: true
 *
 * 示例 2:
 * 输入:pattern = "abba", s = "dog cat cat fish"
 * 输出: false
 *
 * 示例 3:
 * 输入: pattern = "aaaa", s = "dog cat cat dog"
 * 输出: false
 *
 * 提示:
 * 1 <= pattern.length <= 300
 * pattern只包含小写英文字母
 * 1 <= s.length <= 3000
 * s只包含小写英文字母和' '
 * s不包含任何前导或尾随对空格
 * s中每个单词都被单个空格分隔
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        String[] marks = new String[26];
        for (int i = 0; i < 26; i++) {
            marks[i] = "";
        }
        for (int i = 0; i < pattern.length(); i++) {
            int index = pattern.charAt(i) - 'a';
            if ("".equals(marks[index])) {
                marks[index] = words[i];
            } else {
                if (!marks[index].equals(words[i])) {
                    return false;
                }
            }
        }
        //i相同的value必须不同
        for (int i = 0; i < 25; i++) {
            String cur = marks[i];
            if ("".equals(cur)) {
                continue;
            }
            for (int j = i + 1; j < 26; j++) {
                if ("".equals(marks[j])) {
                    continue;
                }
                if (cur.equals(marks[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
