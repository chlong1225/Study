package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/4/14.
 * description : 最富有客户的资产总量
 *
 * 给你一个m x n的整数网格accounts，其中accounts[i][j]是第i位客户在第j家银行托管的资产数量。返回最富有客户所拥有的资产总量。
 * 客户的资产总量就是他们在各家银行托管的资产数量之和。最富有客户就是资产总量最大的客户。
 *
 * 示例 1：
 * 输入：accounts = [[1,2,3],[3,2,1]]
 * 输出：6
 * 解释：
 * 第 1 位客户的资产总量 = 1 + 2 + 3 = 6
 * 第 2 位客户的资产总量 = 3 + 2 + 1 = 6
 * 两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。
 *
 * 示例 2：
 * 输入：accounts = [[1,5],[7,3],[3,5]]
 * 输出：10
 * 解释：
 * 第 1 位客户的资产总量 = 6
 * 第 2 位客户的资产总量 = 10
 * 第 3 位客户的资产总量 = 8
 * 第 2 位客户是最富有的，资产总量是 10
 *
 * 示例 3：
 * 输入：accounts = [[2,8,7],[7,1,3],[1,9,5]]
 * 输出：17
 *
 * 提示：
 * m ==accounts.length
 * n ==accounts[i].length
 * 1 <= m, n <= 50
 * 1 <= accounts[i][j] <= 100
 */
public class MaximumWealth {

    public int maximumWealth(int[][] accounts) {
        int m = accounts.length;
        int n = accounts[0].length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max += accounts[0][i];
        }
        for (int i = 1; i < m; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += accounts[i][j];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
