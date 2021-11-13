package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/11/13.
 * description :
 *
 * 给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
 * 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
 * 由于答案可能很大，只需要返回 答案 mod 10^9 + 7 的值。
 *
 * 示例 1:
 * 输入: n = 3, k = 0
 * 输出: 1
 * 解释:
 * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
 *
 * 示例 2:
 * 输入: n = 3, k = 1
 * 输出: 2
 * 解释:
 * 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
 *
 * 说明:
 * n的范围是 [1, 1000] 并且k的范围是 [0, 1000]。
 */
public class KReverseCount {

    public static int kInversePairs(int n, int k) {
        int mod = 1000000007;
        if (n == 1) {
            return k == 0 ? 1 : 0;
        }
        //1,n个整数最大的逆序数量
        int max = n * (n - 1) / 2;
        if (k > max) {
            return 0;
        }
        //2,经分析逆序数量具有对称性,同时处理特殊场景
        if (k > max - k) {
            k = max - k;
        }
        if (k == 0) {
            return 1;
        }
        if (k == 1) {
            return n - 1;
        }
        //3,通过找规律
        int[][] marks = new int[n + 1][k + 1];
        //4,定义边界条件
        for (int i = 2; i <= n; i++) {
            marks[i][0] = 1;
            marks[i][1] = i - 1;
        }
        for (int i = 2; i <= n; i++) {
            int maxCount = i * (i - 1) / 2;
            for (int j = 2; j <= k; j++) {
                if (j > maxCount) {
                    break;
                }
                if (j > maxCount - j) {
                    //利用对称性
                    marks[i][j] = marks[i][maxCount - j];
                } else {
                    int indexMin = j - i + 1;
                    if (indexMin < 0) {
                        indexMin = 0;
                    }
                    for (int index = j; index >= indexMin; index--) {
                        marks[i][j] += marks[i - 1][index];
                        marks[i][j] %= mod;
                    }
                }
            }
        }
        return marks[n][k];
    }

    public static int kInversePairs2(int n, int k) {
        int mod = 1000000007;
        if (n == 1) {
            return k == 0 ? 1 : 0;
        }
        //1,n个整数最大的逆序数量
        int max = n * (n - 1) / 2;
        if (k > max) {
            return 0;
        }
        //2,经分析逆序数量具有对称性,同时处理特殊场景
        if (k > max - k) {
            k = max - k;
        }
        if (k == 0) {
            return 1;
        }
        if (k == 1) {
            return n - 1;
        }
        //动态规划方程:f(n)(k) = f(n-1)(k)+f(n-1)(k-1)+...+f(n-1)(k+1-n),总计n个求和。如果k+1-n<0。累计求和到f(n-1)(0)
        //优化方程:f(n)(k) - f(n)(k-1) = f(n-1)(k)-f(n-1)(k-n)。需要考虑k<n的情况
        int[][] marks = new int[2][k + 1];
        marks[0][0] = 1;
        marks[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                int pre = (i - 1) & 1;
                int cur = i & 1;
                marks[cur][j] = marks[cur][j - 1] + marks[pre][j] - (j >= i ? marks[pre][j - i] : 0);
                if (marks[cur][j] > mod) {
                    marks[cur][j] %= mod;
                } else if (marks[cur][j] < 0) {
                    marks[cur][j] += mod;
                }
            }
        }
        return marks[n & 1][k];
    }
}
