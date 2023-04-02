package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/3/27
 * @author chenglong
 * description : 统计只差一个字符的子串数目
 *
 * 给你两个字符串s和t，请你找出s中的非空子串的数目，这些子串满足替换一个不同字符以后，是t串的子串。换言之，请你找到s和t串中恰好只有一个字符不同的子字符串对的数目。
 * 比方说，"computer"and"computation"只有一个字符不同：'e'/'a'，所以这一对子字符串会给答案加 1 。
 * 请你返回满足上述条件的不同子字符串对数目。
 * 一个子字符串是一个字符串中连续的字符。
 *
 * 示例 1：
 * 输入：s = "aba", t = "baba"
 * 输出：6
 * 解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * 加粗部分分别表示 s 和 t 串选出来的子字符串。
 *
 * 示例 2：
 * 输入：s = "ab", t = "bb"
 * 输出：3
 * 解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
 * ("ab", "bb")
 * ("ab", "bb")
 * ("ab", "bb")
 * 加粗部分分别表示 s 和 t 串选出来的子字符串。
 *
 * 示例 3：
 * 输入：s = "a", t = "a"
 * 输出：0
 *
 * 示例 4：
 * 输入：s = "abe", t = "bbc"
 * 输出：10
 *
 * 提示：
 * 1 <= s.length, t.length <= 100
 * s和t都只包含小写英文字母。
 */
public class CountSubstrings {

    public int countSubstrings(String s, String t) {
        int m = s.length();
        int n = t.length();
        //最长子串
        int maxSize = Math.min(m, n);
        int count = 0;
        for (int i = 1; i <= maxSize; i++) {
            for (int sIndex = 0; sIndex <= m - i; sIndex++) {
                for (int tIndex = 0; tIndex <= n - i; tIndex++) {
                    int diff = checkDiff(sIndex, tIndex, i, s, t);
                    if (diff == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int countSubstrings2(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] lefts = new int[m + 1][n + 1];
        int[][] rights = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    lefts[i + 1][j + 1] = lefts[i][j] + 1;
                } else {
                    lefts[i + 1][j + 1] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    rights[i][j] = rights[i + 1][j + 1] + 1;
                } else {
                    rights[i][j] = 0;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    count += (lefts[i][j] + 1) * (rights[i + 1][j + 1] + 1);
                }
            }
        }
        return count;
    }

    private int checkDiff(int sIndex, int tIndex, int n, String s, String t) {
        int diff = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(sIndex + i) != t.charAt(tIndex + i)) {
                diff++;
                if (diff > 1) {
                    break;
                }
            }
        }
        return diff;
    }
}
