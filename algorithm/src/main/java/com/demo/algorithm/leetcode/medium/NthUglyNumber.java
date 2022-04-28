package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/4/28
 * @author chenglong
 * description : 丑数II
 *
 * 给你一个整数n，请你找出并返回第n个丑数 。
 * 丑数 就是只包含质因数2、3 和/或5的正整数。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 * 提示：
 * 1 <= n <= 1690
 */
public class NthUglyNumber {

    public int nthUglyNumber(int n) {
        /**
         * 分析：由于当前数字/2或3或5能够在之前的数字中找到，即当前数字可以通过之前的数字转换过来，这里可以使用动态规划
         */
        int[] marks = new int[n + 1];
        //1，初始化条件
        marks[1] = 1;
        //2,定义三个指针，分别标记*2，*3，*5的位置
        int index2 = 1;
        int index3 = 1;
        int index5 = 1;
        for (int i = 2; i <= n; i++) {
            int a = marks[index2] * 2;
            int b = marks[index3] * 3;
            int c = marks[index5] * 5;
            int min = Math.min(Math.min(a, b), c);
            marks[i] = min;
            if (min == a) {
                index2++;
            }
            if (min == b) {
                index3++;
            }
            if (min == c) {
                index5++;
            }
        }
        return marks[n];
    }
}
