package com.demo.algorithm.leetcode.contest.doubleweek90;

/**
 * create on 2022/10/31
 * @author chenglong
 * description : 差值数组不同的字符串
 *
 * 给你一个字符串数组words，每一个字符串长度都相同，令所有字符串的长度都为n。
 * 每个字符串words[i]可以被转化为一个长度为n-1的差值整数数组difference[i]，其中对于0 <= j <= n-2有difference[i][j] = words[i][j+1] - words[i][j]。
 * 注意两个字母的差值定义为它们在字母表中位置之差，也就是说'a'的位置是0，'b'的位置是1，'z'的位置是25。
 * 比方说，字符串"acb"的差值整数数组是[2 - 0, 1 - 2] = [2, -1]。
 * words中所有字符串 除了一个字符串以外，其他字符串的差值整数数组都相同。你需要找到那个不同的字符串。
 * 请你返回words中差值整数数组不同的字符串。
 *
 * 示例 1：
 * 输入：words = ["adc","wzy","abc"]
 * 输出："abc"
 * 解释：
 * - "adc" 的差值整数数组是 [3 - 0, 2 - 3] = [3, -1] 。
 * - "wzy" 的差值整数数组是 [25 - 22, 24 - 25]= [3, -1] 。
 * - "abc" 的差值整数数组是 [1 - 0, 2 - 1] = [1, 1] 。
 * 不同的数组是 [1, 1]，所以返回对应的字符串，"abc"。
 *
 * 示例 2：
 * 输入：words = ["aaa","bob","ccc","ddd"]
 * 输出："bob"
 * 解释：除了"bob" 的差值整数数组是 [13, -13] 以外，其他字符串的差值整数数组都是 [0, 0]。
 *
 * 提示：
 * 3 <= words.length <= 100
 * n == words[i].length
 * 2 <= n <= 20
 * words[i]只含有小写英文字母。
 */
public class OddString {

    public String oddString(String[] words) {
        int[] dates1 = getDiff(words[0]);
        int[] dates2 = getDiff(words[1]);
        int[] dates3 = getDiff(words[2]);
        if (isSame(dates1, dates2)) {
            if (isSame(dates1, dates3)) {
                for (int i = 3; i < words.length; i++) {
                    int[] dates = getDiff(words[i]);
                    if (!isSame(dates1, dates)) {
                        return words[i];
                    }
                }
                return "";
            } else {
                return words[2];
            }
        } else {
            if (isSame(dates1, dates3)) {
                return words[1];
            } else {
                return words[0];
            }
        }
    }

    private boolean isSame(int[] dates1, int[] dates2) {
        int length = dates1.length;
        for (int i = 0; i < length; i++) {
            if (dates1[i] != dates2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] getDiff(String word) {
        int n = word.length();
        int[] diff = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = word.charAt(i + 1) - word.charAt(i);
        }
        return diff;
    }
}
