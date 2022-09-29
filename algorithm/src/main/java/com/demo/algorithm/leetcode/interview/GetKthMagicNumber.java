package com.demo.algorithm.leetcode.interview;

/**
 * create on 2022/9/29
 * @author chenglong
 * description : 面试题17.09. 第k个数
 *
 * 有些数的素因子只有3，5，7，请设计一个算法找出第k个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是1，3，5，7，9，15，21。
 *
 * 示例 1:
 * 输入: k = 5
 * 输出: 9
 */
public class GetKthMagicNumber {

    public int getKthMagicNumber(int k) {
        int p3 = 1;
        int p5 = 1;
        int p7 = 1;
        int[] marks = new int[k + 1];
        marks[1] = 1;
        for (int i = 2; i <= k; i++) {
            int a3 = marks[p3] * 3;
            int a5 = marks[p5] * 5;
            int a7 = marks[p7] * 7;
            marks[i] = Math.min(Math.min(a3, a5), a7);
            if (marks[i] == a3) {
                p3++;
            }
            if (marks[i] == a5) {
                p5++;
            }
            if (marks[i] == a7) {
                p7++;
            }
        }
        return marks[k];
    }
}
