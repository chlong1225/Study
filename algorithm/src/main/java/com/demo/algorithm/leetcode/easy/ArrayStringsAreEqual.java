package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/11/1
 * @author chenglong
 * description : 检查两个字符串数组是否相等
 *
 * 给你两个字符串数组word1和word2。如果两个数组表示的字符串相同返回true；否则返回false。
 * 数组表示的字符串是由数组中的所有元素按顺序连接形成的字符串。
 *
 * 示例 1：
 * 输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
 * 输出：true
 * 解释：
 * word1 表示的字符串为 "ab" + "c" -> "abc"
 * word2 表示的字符串为 "a" + "bc" -> "abc"
 * 两个字符串相同，返回 true
 *
 * 示例 2：
 * 输入：word1 = ["a", "cb"], word2 = ["ab", "c"]
 * 输出：false
 *
 * 示例 3：
 * 输入：word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * 输出：true
 *
 * 提示：
 * 1 <= word1.length, word2.length <= 10^3
 * 1 <= word1[i].length, word2[i].length <= 10^3
 * 1 <= sum(word1[i].length), sum(word2[i].length) <= 10^3
 * word1[i] 和 word2[i] 由小写字母组成
 */
public class ArrayStringsAreEqual {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        //1，判断连接后的两个字符串长度是否相同
        int length = 0;
        for (int i = 0; i < word1.length; i++) {
            length += word1[i].length();
        }
        for (int i = 0; i < word2.length; i++) {
            length -= word2[i].length();
        }
        if (length != 0) {
            return false;
        }
        //当前字符串在word2中的位置
        int index1 = 0;
        //当前比较的字符在word2[index1]中的位置
        int index2 = 0;
        for (int i = 0; i < word1.length; i++) {
            String cur = word1[i];
            for (int j = 0; j < cur.length(); j++) {
                if (cur.charAt(j) != word2[index1].charAt(index2)) {
                    return false;
                }
                index2++;
                if (word2[index1].length() == index2) {
                    index1++;
                    index2 = 0;
                }
            }
        }
        return true;
    }
}
