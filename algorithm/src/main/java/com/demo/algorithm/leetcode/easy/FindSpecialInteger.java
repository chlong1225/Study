package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/1/22
 * @author chenglong
 * description : 有序数组中出现次数超过25%的元素
 *
 * 给你一个非递减的有序整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的25%。
 * 请你找到并返回这个整数
 *
 * 示例：
 * 输入：arr = [1,2,2,6,6,6,6,7,10]
 * 输出：6
 *
 * 提示：
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 */
public class FindSpecialInteger {

    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return arr[0];
        }
        int maxCount = 1;
        int max = arr[0];
        int count = 1;
        int pre = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] == pre) {
                count++;
            } else {
                if (count > maxCount) {
                    maxCount = count;
                    max = pre;
                }
                count = 1;
            }
            pre = arr[i];
        }
        if (count > maxCount) {
            max = arr[n - 1];
        }
        return max;
    }
}
