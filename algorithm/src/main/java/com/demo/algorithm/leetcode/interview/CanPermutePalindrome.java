package com.demo.algorithm.leetcode.interview;

/**
 * Created by chl on 2022/5/20.
 * description : 面试题01.04. 回文排列
 *
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 * 回文串不一定是字典当中的单词。
 *
 * 示例1：
 * 输入："tactcoa"
 * 输出：true（排列有"tacocat"、"atcocta"，等等）
 */
public class CanPermutePalindrome {

    public boolean canPermutePalindrome(String s) {
        int length = s.length();
        int[] counts = new int[128];
        for (int i = 0; i < length; i++) {
            counts[s.charAt(i)]++;
        }
        int num = 0;
        for (int i = 0; i < 128; i++) {
            if (counts[i] % 2 == 1) {
                num++;
            }
        }
        return num <= 1;
    }
}
