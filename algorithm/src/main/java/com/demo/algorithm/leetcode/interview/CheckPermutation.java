package com.demo.algorithm.leetcode.interview;

/**
 * create on 2022/5/20
 * @author chenglong
 * description : 面试题01.02. 判定是否互为字符重排
 *
 * 给定两个字符串s1和s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 *
 * 示例 2：
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 *
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 */
public class CheckPermutation {

    public boolean CheckPermutation(String s1, String s2) {
        int length = s1.length();
        if (s2.length() != length) {
            return false;
        }
        int[] counts = new int[128];
        for (int i = 0; i < length; i++) {
            counts[s1.charAt(i)]++;
            counts[s2.charAt(i)]--;
        }
        for (int i = 0; i < 128; i++) {
            if (counts[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
