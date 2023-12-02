package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/22
 * @author chenglong
 * description : 比特位计数
 *
 * 给你一个整数n，对于0<=i<=n中的每个i，计算其二进制表示中1的个数，返回一个长度为n+1的数组ans作为答案。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 * 提示：
 * 0 <= n <= 10^5
 *
 * 进阶：
 * 很容易就能实现时间复杂度为 O(nlogn)的解决方案，你可以在线性时间复杂度O(n)内用一趟扫描解决此问题吗？
 * 你能不使用任何内置函数解决此问题吗？（如，C++中的__builtin_popcount）
 */
public class CountBits {

    public int[] countBits(int n) {
        int[] answers = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            answers[i] = Integer.bitCount(i);
        }
        return answers;
    }
}
