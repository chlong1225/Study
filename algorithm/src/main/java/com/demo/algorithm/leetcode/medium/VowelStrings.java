package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/6/2
 * @author chenglong
 * description : 统计范围内的元音字符串数
 *
 * 给你一个下标从0开始的字符串数组words以及一个二维整数数组queries。
 * 每个查询queries[i]=[li, ri]会要求我们统计在words中下标在li到ri范围内（包含这两个值）并且以元音开头和结尾的字符串的数目。
 * 返回一个整数数组，其中数组的第i个元素对应第i个查询的答案。
 * 注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。
 *
 * 示例 1：
 * 输入：words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
 * 输出：[2,3,0]
 * 解释：以元音开头和结尾的字符串是 "aba"、"ece"、"aa" 和 "e" 。
 * 查询 [0,2] 结果为 2（字符串 "aba" 和 "ece"）。
 * 查询 [1,4] 结果为 3（字符串 "ece"、"aa"、"e"）。
 * 查询 [1,1] 结果为 0 。
 * 返回结果 [2,3,0] 。
 *
 * 示例 2：
 * 输入：words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
 * 输出：[3,2,1]
 * 解释：每个字符串都满足这一条件，所以返回 [3,2,1] 。
 *
 * 提示：
 * 1 <= words.length <= 10^5
 * 1 <= words[i].length <= 40
 * words[i] 仅由小写英文字母组成
 * sum(words[i].length) <= 3 * 10^5
 * 1 <= queries.length <= 10^5
 * 0 <= queries[j][0] <= queries[j][1] <words.length
 */
public class VowelStrings {

    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] dates = new int[n];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (word.length() == 1) {
                if (checkChar(word.charAt(0))) {
                    dates[i] = 1;
                } else {
                    dates[i] = 0;
                }
            } else {
                if (checkChar(word.charAt(0)) && checkChar(word.charAt(word.length() - 1))) {
                    dates[i] = 1;
                } else {
                    dates[i] = 0;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            dates[i] += dates[i - 1];
        }
        int m = queries.length;
        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            if (l == 0) {
                result[i] = dates[r];
            } else {
                result[i] = dates[r] - dates[l - 1];
            }
        }
        return result;
    }

    private boolean checkChar(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }
}
