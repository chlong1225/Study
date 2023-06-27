package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/6/27
 * @author chenglong
 * description : 删除一次得到子数组最大和
 *
 * 给你一个整数数组，返回它的某个非空子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），
 * （删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
 * 注意，删除一个元素后，子数组不能为空。
 *
 * 示例 1：
 * 输入：arr = [1,-2,0,3]
 * 输出：4
 * 解释：我们可以选出 [1, -2, 0, 3]，然后删掉 -2，这样得到 [1, 0, 3]，和最大。
 *
 * 示例 2：
 * 输入：arr = [1,-2,-2,3]
 * 输出：3
 * 解释：我们直接选出 [3]，这就是最大和。
 *
 * 示例 3：
 * 输入：arr = [-1,-1,-1,-1]
 * 输出：-1
 * 解释：最后得到的子数组不能为空，所以我们不能选择 [-1] 并从中删去 -1 来得到 0。
 *      我们应该直接选择 [-1]，或者选择 [-1, -1] 再从中删去一个 -1。
 *
 * 提示：
 * 1 <= arr.length <= 10^5
 * -10^4<= arr[i] <= 10^4
 */
public class MaximumSum {

    public int maximumSum(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return arr[0];
        }
        /**
         * marks[i][j]: 当前位置的最大总和
         * i：遍历到的位置
         * j：0：对应没有删除，1，对应删除一个元素
         */
        int[][] marks = new int[n][2];
        marks[0][0] = arr[0];
        marks[0][1] = 0;
        int result = arr[0];
        for (int i = 1; i < n; i++) {
            marks[i][0] = arr[i];
            if (marks[i - 1][0] > 0) {
                marks[i][0] += marks[i - 1][0];
            }
            marks[i][1] = Math.max(marks[i - 1][0], marks[i - 1][1] + arr[i]);
            int max = Math.max(marks[i][0], marks[i][1]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }
}
