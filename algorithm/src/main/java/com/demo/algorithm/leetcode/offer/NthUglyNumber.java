package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/23.
 * description : 剑指Offer49. 丑数
 *
 * 我们把只包含质因子2、3和5的数称作丑数（Ugly Number）。求按从小到大的顺序的第n个丑数。
 *
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 *
 * 1是丑数。
 * n不超过1690。
 */
public class NthUglyNumber {

    public int nthUglyNumber(int n) {
        if (n <= 6) {
            return n;
        }
        int[] marks = new int[n + 1];
        marks[1] = 1;
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        for (int i = 2; i <= n; i++) {
            int a = marks[p2] * 2;
            int b = marks[p3] * 3;
            int c = marks[p5] * 5;
            marks[i] = Math.min(Math.min(a, b), c);
            if (marks[i] == a) {
                p2++;
            }
            if (marks[i] == b) {
                p3++;
            }
            if (marks[i] == c) {
                p5++;
            }
        }
        return marks[n];
    }
}
