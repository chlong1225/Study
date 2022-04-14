package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/14.
 * description : 剑指Offer65. 不用加减乘除做加法
 *
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 * 示例:
 * 输入: a = 1, b = 1
 * 输出: 2
 *
 * 提示：
 * a,b均可能是负数或0
 * 结果不会溢出32位整数
 */
public class Add {

    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }
}
