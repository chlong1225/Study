package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/4
 * @author chenglong
 * description : 有效的山脉数组
 *
 * 给定一个整数数组arr，如果它是有效的山脉数组就返回true，否则返回false。
 * 让我们回顾一下，如果arr满足下述条件，那么它是一个山脉数组：
 * arr.length >= 3
 * 在 0 < i < arr.length - 1 条件下，存在i使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 *
 * 示例 1：
 * 输入：arr = [2,1]
 * 输出：false
 *
 * 示例 2：
 * 输入：arr = [3,5,5]
 * 输出：false
 *
 * 示例 3：
 * 输入：arr = [0,3,2,1]
 * 输出：true
 *
 * 提示：
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^4
 */
public class MountainArray {

    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return false;
        }
        if (arr[0] >= arr[1]) {
            return false;
        }
        boolean isAdd = true;
        for (int i = 2; i < n; i++) {
            if (arr[i] == arr[i - 1]) {
                return false;
            }
            if (arr[i] > arr[i - 1]) {
                if (!isAdd) {
                    return false;
                }
            } else {
                if (isAdd) {
                    isAdd = false;
                }
            }
        }
        return !isAdd;
    }
}
