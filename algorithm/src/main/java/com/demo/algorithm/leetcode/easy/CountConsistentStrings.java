package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/11/8
 * @author chenglong
 * description : 统计一致字符串的数目
 *
 * 给你一个由不同字符组成的字符串allowed和一个字符串数组words。如果一个字符串的每一个字符都在allowed中，就称这个字符串是一致字符串。
 * 请你返回words数组中一致字符串的数目。
 *
 * 示例 1：
 * 输入：allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
 * 输出：2
 * 解释：字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
 *
 * 示例 2：
 * 输入：allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
 * 输出：7
 * 解释：所有字符串都是一致的。
 *
 * 示例 3：
 * 输入：allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
 * 输出：4
 * 解释：字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。
 *
 * 提示：
 * 1 <= words.length <= 10^4
 * 1 <= allowed.length <= 26
 * 1 <= words[i].length <= 10
 * allowed中的字符互不相同。
 * words[i]和allowed只包含小写英文字母。
 */
public class CountConsistentStrings {

    public int countConsistentStrings(String allowed, String[] words) {
        //1，构建比较的数据源
        boolean[] compare = new boolean[26];
        int length = allowed.length();
        for (int i = 0; i < length; i++) {
            compare[allowed.charAt(i) - 'a'] = true;
        }
        //2，依次比较
        length = words.length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (isContain(words[i], compare)) {
                count++;
            }
        }
        return count;
    }

    private boolean isContain(String word, boolean[] compare) {
        int length = word.length();
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'a';
            if (!compare[index]) {
                return false;
            }
        }
        return true;
    }
}
