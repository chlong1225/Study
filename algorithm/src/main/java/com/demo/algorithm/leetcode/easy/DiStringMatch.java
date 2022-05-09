package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/5/9
 * @author chenglong
 * description : 增减字符串匹配
 *
 * 由范围[0,n]内所有整数组成的n+1个整数的排列序列可以表示为长度为n的字符串s，其中:
 * 如果perm[i] < perm[i + 1]，那么s[i] == 'I'
 * 如果perm[i] > perm[i + 1]，那么 s[i] == 'D'
 * 给定一个字符串s，重构排列perm并返回它。如果有多个有效排列perm，则返回其中任何一个 。
 *
 * 示例 1：
 * 输入：s = "IDID"
 * 输出：[0,4,1,3,2]
 *
 * 示例 2：
 * 输入：s = "III"
 * 输出：[0,1,2,3]
 *
 * 示例 3：
 * 输入：s = "DDI"
 * 输出：[3,2,0,1]
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 只包含字符"I"或"D"
 */
public class DiStringMatch {

    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] result = new int[n + 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                result[i] = index;
                index++;
            }
        }
        result[n] = index;
        index++;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'D') {
                result[i] = index;
                index++;
            }
        }
        return result;
    }
}
