package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/1/22
 * @author chenglong
 * description : 和为零的N个不同整数
 *
 * 给你一个整数n，请你返回任意一个由n个各不相同的整数组成的数组，并且这n个数相加和为0。
 *
 * 示例 1：
 * 输入：n = 5
 * 输出：[-7,-1,1,3,4]
 * 解释：这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]。
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：[-1,0,1]
 *
 * 示例 3：
 * 输入：n = 1
 * 输出：[0]
 *
 * 提示：
 * 1 <= n <= 1000
 */
public class SumZero {

    public int[] sumZero(int n) {
        int[] arr = new int[n];
        if (n == 1) {
            arr[0] = 0;
        } else {
            int count = n / 2;
            for (int i = 0; i < count; i++) {
                arr[i] = i + 1;
                arr[i + count] = -(i + 1);
            }
            if (n % 2 == 1) {
                arr[n - 1] = 0;
            }
        }
        return arr;
    }
}
