package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/3/28
 * @author chenglong
 * description : 最短公共超序列
 *
 * 给出两个字符串str1和str2，返回同时以str1和str2作为子序列的最短字符串。如果答案不止一个，则可以返回满足条件的任意一个答案。
 * （如果从字符串T中删除一些字符（也可能不删除，并且选出的这些字符可以位于T中的任意位置），可以得到字符串S，那么S就是T的子序列）
 *
 * 示例：
 * 输入：str1 = "abac", str2 = "cab"
 * 输出："cabac"
 * 解释：
 * str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。
 * str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
 * 最终我们给出的答案是满足上述属性的最短字符串。
 *
 * 提示：
 * 1 <= str1.length, str2.length <= 1000
 * str1和str2都由小写英文字母组成。
 */
public class ShortestCommonSupersequence {

    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        //记录最短长度
        int[][] marks = new int[m + 1][n + 1];
        for (int i = 1; i < m; i++) {
            marks[i][0] = i;
        }
        for (int i = 1; i < n; i++) {
            marks[0][i] = i;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    marks[i + 1][j + 1] = marks[i][j] + 1;
                } else {
                    marks[i + 1][j + 1] = Math.min(marks[i][j + 1], marks[i + 1][j]) + 1;
                }
            }
        }
        int length = marks[m][n];
        char[] result = new char[marks[m][n]];
        int index1 = m - 1;
        int index2 = n - 1;
        int index = length - 1;
        while (index1 >= 0 || index2 >= 0) {
            if (index1 < 0) {
                result[index] = str2.charAt(index2);
                index2--;
            } else if (index2 < 0) {
                result[index] = str1.charAt(index1);
                index1--;
            } else {
                if (str1.charAt(index1) == str2.charAt(index2)) {
                    result[index] = str1.charAt(index1);
                    index1--;
                    index2--;
                } else {
                    if (marks[index1 + 1][index2 + 1] == marks[index1 + 1][index2] + 1) {
                        result[index] = str2.charAt(index2);
                        index2--;
                    } else {
                        result[index] = str1.charAt(index1);
                        index1--;
                    }
                }
            }
            index--;
        }
        return new String(result);
    }
}
