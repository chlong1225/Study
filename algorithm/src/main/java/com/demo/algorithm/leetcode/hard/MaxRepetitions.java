package com.demo.algorithm.leetcode.hard;

/**
 * create on 2024/1/2
 * @author chenglong
 * description : 统计重复个数
 *
 * 定义str=[s,n]表示str由n个字符串s连接构成。
 * 例如，str == ["abc", 3] =="abcabcabc" 。
 * 如果可以从s2中删除某些字符使其变为s1，则称字符串s1可以从字符串s2获得。
 * 例如，根据定义，s1 = "abc" 可以从 s2 = "abdbec" 获得。
 * 现在给你两个字符串s1和s2和两个整数n1和n2。由此构造得到两个字符串，其中str1 = [s1, n1]、str2 = [s2, n2] 。
 * 请你找出一个最大整数 m ，以满足 str = [str2, m] 可以从 str1 获得。
 *
 * 示例 1：
 * 输入：s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：s1 = "acb", n1 = 1, s2 = "acb", n2 = 1
 * 输出：1
 *
 * 提示：
 * 1 <= s1.length, s2.length <= 100
 * s1 和 s2 由小写英文字母组成
 * 1 <= n1, n2 <= 10^6
 */
public class MaxRepetitions {

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        /**
         * 转换可知：str = [s2,n2*m]，需要查找str1中有多少个s2
         */

        return 0;
    }
}
