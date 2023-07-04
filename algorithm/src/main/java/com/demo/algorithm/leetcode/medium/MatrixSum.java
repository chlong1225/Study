package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2023/7/4
 * @author chenglong
 * description : 矩阵中的和
 *
 * 给你一个下标从0开始的二维整数数组nums。一开始你的分数为0。你需要执行以下操作直到矩阵变为空：
 * 矩阵中每一行选取最大的一个数，并删除它。如果一行中有多个最大的数，选择任意一个并删除。
 * 在步骤1删除的所有数字中找到最大的一个数字，将它添加到你的分数中。
 * 请你返回最后的分数。
 *
 * 示例 1：
 * 输入：nums = [[7,2,1],[6,4,2],[6,5,3],[3,2,1]]
 * 输出：15
 * 解释：第一步操作中，我们删除 7 ，6 ，6 和 3 ，将分数增加 7 。下一步操作中，删除 2 ，4 ，5 和 2 ，将分数增加 5 。
 * 最后删除 1 ，2 ，3 和 1 ，将分数增加 3 。所以总得分为 7 + 5 + 3 = 15 。
 *
 * 示例 2：
 * 输入：nums = [[1]]
 * 输出：1
 * 解释：我们删除 1 并将分数增加 1 ，所以返回 1 。
 *
 * 提示：
 * 1 <= nums.length <= 300
 * 1 <= nums[i].length <= 500
 * 0 <= nums[i][j] <= 10^3
 */
public class MatrixSum {

    public int matrixSum(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;
        //1，对每行进行排序
        for (int i = 0; i < m; i++) {
            Arrays.sort(nums[i]);
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int max = nums[0][i];
            for (int j = 1; j < m; j++) {
                if (nums[j][i] > max) {
                    max = nums[j][i];
                }
            }
            sum += max;
        }
        return sum;
    }
}
