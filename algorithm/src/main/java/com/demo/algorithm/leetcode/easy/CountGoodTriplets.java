package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/21
 * @author chenglong
 * description : 统计好三元组
 *
 * 给你一个整数数组arr，以及a、b 、c 三个整数。请你统计其中好三元组的数量。
 * 如果三元组(arr[i], arr[j], arr[k])满足下列全部条件，则认为它是一个好三元组 。
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 * 其中|x|表示x的绝对值。
 * 返回好三元组的数量 。
 *
 * 示例 1：
 * 输入：arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
 * 输出：4
 * 解释：一共有 4 个好三元组：[(3,0,1), (3,0,1), (3,1,1), (0,1,1)] 。
 *
 * 示例 2：
 * 输入：arr = [1,1,2,2,3], a = 0, b = 0, c = 1
 * 输出：0
 * 解释：不存在满足所有条件的三元组。
 *
 * 提示：
 * 3 <= arr.length <= 100
 * 0 <= arr[i] <= 1000
 * 0 <= a, b, c <= 1000
 */
public class CountGoodTriplets {

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        int n = arr.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (Math.abs(arr[i] - arr[j]) <= a) {
                    for (int k = j + 1; k < n; k++) {
                        int tem1 = Math.abs(arr[j] - arr[k]);
                        int tem2 = Math.abs(arr[i] - arr[k]);
                        if (tem1 <= b && tem2 <= c) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
