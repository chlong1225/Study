package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/4/19
 * @author chenglong
 * description : 分隔数组以得到最大和
 *
 * 给你一个整数数组arr，请你将该数组分隔为长度最多为k的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 * 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个32位整数。
 *
 * 示例 1：
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：数组变为 [15,15,15,9,10,10,10]
 *
 * 示例 2：
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 *
 * 示例 3：
 * 输入：arr = [1], k = 1
 * 输出：1
 *
 * 提示：
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^9
 * 1 <= k <= arr.length
 */
public class MaxSumAfterPartitioning {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] marks = new int[n + 1];
        marks[1] = arr[0];
        for (int i = 2; i <= n; i++) {
            int max = arr[i - 1];
            //从i-1 ~ i-k
            int maxSum = marks[i - 1] + max;
            for (int j = 2; j <= Math.min(k, i); j++) {
                int index = i - j;
                if (arr[i - j] > max) {
                    max = arr[i - j];
                }
                int tem = marks[index] + j * max;
                if (tem > maxSum) {
                    maxSum = tem;
                }
            }
            marks[i] = maxSum;
        }
        return marks[n];
    }
}
