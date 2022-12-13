package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/12/13
 * @author chenglong
 * description : 判断句子是否为全字母句
 *
 * 全字母句指包含英语字母表中每个字母至少一次的句子。
 * 给你一个仅由小写英文字母组成的字符串sentence，请你判断sentence是否为全字母句。
 * 如果是返回true；否则返回false。
 *
 * 示例 1：
 * 输入：sentence = "thequickbrownfoxjumpsoverthelazydog"
 * 输出：true
 * 解释：sentence 包含英语字母表中每个字母至少一次。
 *
 * 示例 2：
 * 输入：sentence = "leetcode"
 * 输出：false
 *
 * 提示：
 * 1 <= sentence.length <= 1000
 * sentence 由小写英语字母组成
 */
public class CheckIfPangram {

    public boolean checkIfPangram(String sentence) {
        int length = sentence.length();
        if (length < 26) {
            return false;
        }
        boolean[] marks = new boolean[26];
        int count = 0;
        for (int i = 0; i < length; i++) {
            int index = sentence.charAt(i) - 'a';
            if (!marks[index]) {
                count++;
            }
            marks[index] = true;
        }
        return count == 26;
    }
}
