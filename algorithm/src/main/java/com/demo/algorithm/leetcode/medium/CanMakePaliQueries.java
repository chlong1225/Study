package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/6/15
 * @author chenglong
 * description : 构建回文串检测
 *
 * 给你一个字符串s，请你对s的子串进行检测。
 * 每次检测，待检子串都可以表示为queries[i]=[left,right,k]。我们可以重新排列子串s[left], ..., s[right]，并从中选择最多k项替换成任何小写英文字母。
 * 如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为true，否则结果为false。
 * 返回答案数组answer[]，其中answer[i]是第i个待检子串queries[i]的检测结果。
 * 注意：在替换时，子串中的每个字母都必须作为独立的项进行计数，也就是说，如果s[left..right]="aaa"且k=2，我们只能替换其中的两个字母。
 * （另外，任何检测都不会修改原始字符串s，可以认为每次检测都是独立的）
 *
 * 示例：
 * 输入：s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
 * 输出：[true,false,false,true,true]
 * 解释：
 * queries[0] : 子串 = "d"，回文。
 * queries[1] :子串 = "bc"，不是回文。
 * queries[2] :子串 = "abcd"，只替换 1 个字符是变不成回文串的。
 * queries[3] :子串 = "abcd"，可以变成回文的 "abba"。 也可以变成 "baab"，先重新排序变成 "bacd"，然后把 "cd" 替换为 "ab"。
 * queries[4] :子串 = "abcda"，可以变成回文的 "abcba"。
 *
 * 提示：
 * 1 <= s.length,queries.length<= 10^5
 * 0 <= queries[i][0] <= queries[i][1] <s.length
 * 0 <= queries[i][2] <= s.length
 * s 中只有小写英文字母
 */
public class CanMakePaliQueries {

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int length = s.length();
        //1，预处理前缀和，消除偶数次出现的字母，只统计奇数次出现的字母，即二进制显示时出现1的个数
        int[] sums = new int[length];
        sums[0] = 1 << (s.charAt(0) - 'a');
        for (int i = 1; i < length; i++) {
            int cur = 1 << (s.charAt(i) - 'a');
            sums[i] = sums[i - 1] ^ cur;
        }
        int n = queries.length;
        List<Boolean> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int num = sums[queries[i][1]];
            if (queries[i][0] > 0) {
                num = num ^ sums[queries[i][0] - 1];
            }
            //1的个数，即单独字母的数量
            int count = Integer.bitCount(num);
            result.add(count / 2 <= queries[i][2]);
        }
        return result;
    }
}
