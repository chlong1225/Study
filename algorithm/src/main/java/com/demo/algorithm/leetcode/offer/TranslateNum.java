package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer46. 把数字翻译成字符串
 *
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 提示：
 * 0 <= num < 2^31
 */
public class TranslateNum {

    public int translateNum(int num) {
        if (num < 10) {
            return 1;
        }
        String str = "" + num;
        int n = str.length();
        int[] marks = new int[n + 1];
        marks[0] = 1;
        marks[1] = 1;
        for (int i = 2; i <= n; i++) {
            int tem = (str.charAt(i - 2) - '0') * 10 + (str.charAt(i - 1) - '0');
            if (tem < 26 && tem >= 10) {
                marks[i] = marks[i - 1] + marks[i - 2];
            } else {
                marks[i] = marks[i - 1];
            }
        }
        return marks[n];
    }
}
