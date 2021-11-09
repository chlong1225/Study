package com.demo.algorithm.leetcode.medium;

/**
 * create on 11/9/21
 * @author chenglong
 * description : 交错字符串
 *
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串s和t交错的定义与过程如下，其中每个字符串都会被分割成若干非空子字符串：
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 *
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 *
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 *
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *  
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 */
public class InterlaceStr {

    public static boolean isInterleave(String s1, String s2, String s3) {
        //1，比较长度并处理特殊情况
        int length1 = s1 == null ? 0 : s1.length();
        int length2 = s2 == null ? 0 : s2.length();
        int length3 = s3 == null ? 0 : s3.length();
        if (length1 + length2 != length3) {
            return false;
        }
        if (length1 == 0) {
            if (s3 == null) {
                return s2 == null;
            }
            return s3.equals(s2);
        }
        if (length2 == 0) {
            return s3.equals(s1);
        }
        //2，判断s1，s2的字符在s3中是否都能够找到
        int[] counts = new int[26];
        for (int i = 0; i < length1; i++) {
            counts[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < length2; i++) {
            counts[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < length3; i++) {
            int index = s3.charAt(i) - 'a';
            if (counts[index] > 0) {
                counts[index]--;
            } else {
                return false;
            }
        }
        //3，长度相同，s3中的字符包含s1，s2的字符，使用动态规划
        //3.1，定义边界条件
        boolean[][] marks = new boolean[length1][length2];
        marks[0][0] = true;
        //3.2，循环遍历所有的场景
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                //3.3，状态转移方程
                int index = i + j - 1;
                if (index < 0) {
                    continue;
                }
                if (s3.charAt(index) != s1.charAt(i) && s3.charAt(index) != s2.charAt(j)) {
                    continue;
                }
                if (i > 0 && s3.charAt(index) == s1.charAt(i)) {
                    marks[i][j] |= marks[i - 1][j];
                }
                if (j > 0 && s3.charAt(index) == s2.charAt(j)) {
                    marks[i][j] |= marks[i][j - 1];
                }
            }
        }
        return marks[length1 - 1][length2 - 1];
    }
}
