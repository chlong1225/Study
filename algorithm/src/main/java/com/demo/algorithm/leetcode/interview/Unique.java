package com.demo.algorithm.leetcode.interview;

/**
 * create on 2022/5/20
 * @author chenglong
 * description : 面试题01.01. 判定字符是否唯一
 *
 * 实现一个算法，确定一个字符串s的所有字符是否全都不同。
 *
 * 示例 1：
 * 输入: s = "leetcode"
 * 输出: false
 *
 * 示例 2：
 * 输入: s = "abc"
 * 输出: true
 * 限制：
 *
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 */
public class Unique {

    public boolean isUnique(String astr) {
        int[] counts = new int[128];
        int length = astr.length();
        for (int i = 0; i < length; i++) {
            int index = astr.charAt(i);
            if (counts[index] > 0) {
                return false;
            }
            counts[index]++;
        }
        return true;
    }
}
