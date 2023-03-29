package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/3/29
 * @author chenglong
 * description : 统计字典序元音字符串的数目
 *
 * 给你一个整数n，请返回长度为n、仅由元音(a, e, i, o, u) 组成且按字典序排列的字符串数量。
 * 字符串s按字典序排列需要满足：对于所有有效的i，s[i]在字母表中的位置总是与s[i+1]相同或在s[i+1]之前。
 *
 * 示例 1：
 * 输入：n = 1
 * 输出：5
 * 解释：仅由元音组成的 5 个字典序字符串为 ["a","e","i","o","u"]
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：15
 * 解释：仅由元音组成的 15 个字典序字符串为
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"]
 * 注意，"ea" 不是符合题意的字符串，因为 'e' 在字母表中的位置比 'a' 靠后
 *
 * 示例 3：
 * 输入：n = 33
 * 输出：66045
 *
 * 提示：
 * 1 <= n <= 50
 */
public class CountVowelStrings {

    static int[][] marks = new int[51][5];

    static {
        for (int i = 0; i < 5; i++) {
            marks[1][i] = 1;
        }
        for (int i = 2; i <= 50; i++) {
            //a结尾
            marks[i][0] = marks[i - 1][0];
            //e结尾
            marks[i][1] = marks[i - 1][0] + marks[i - 1][1];
            //i结尾
            marks[i][2] = marks[i - 1][0] + marks[i - 1][1] + marks[i - 1][2];
            //o结尾
            marks[i][3] = marks[i - 1][0] + marks[i - 1][1] + marks[i - 1][2] + marks[i - 1][3];
            //u结尾
            marks[i][4] = marks[i - 1][0] + marks[i - 1][1] + marks[i - 1][2] + marks[i - 1][3] + marks[i - 1][4];
        }
    }

    public int countVowelStrings(int n) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += marks[n][i];
        }
        return sum;
    }
}
