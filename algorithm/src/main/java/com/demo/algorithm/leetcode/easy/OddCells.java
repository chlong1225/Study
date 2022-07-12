package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/7/12
 * @author chenglong
 * description : 奇数值单元格的数目
 *
 * 给你一个m x n的矩阵，最开始的时候，每个单元格中的值都是 0。
 * 另有一个二维索引数组indices，indices[i] = [ri, ci] 指向矩阵中的某个位置，其中 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
 * 对 indices[i] 所指向的每个位置，应同时执行下述增量操作：
 * ri 行上的所有单元格，加 1 。
 * ci 列上的所有单元格，加 1 。
 * 给你m、n和indices。请你在执行完所有indices指定的增量操作后，返回矩阵中奇数值单元格的数目。
 *
 * 示例 1：
 * 输入：m = 2, n = 3, indices = [[0,1],[1,1]]
 * 输出：6
 * 解释：最开始的矩阵是 [[0,0,0],[0,0,0]]。
 * 第一次增量操作后得到 [[1,2,1],[0,1,0]]。
 * 最后的矩阵是 [[1,3,1],[1,3,1]]，里面有6个奇数。
 *
 * 示例 2：
 * 输入：m = 2, n = 2, indices = [[1,1],[0,0]]
 * 输出：0
 * 解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
 *
 * 提示：
 * 1 <= m, n <= 50
 * 1 <= indices.length <= 100
 * 0 <= ri < m
 * 0 <= ci < n
 *
 * 进阶：你可以设计一个时间复杂度为 O(n + m + indices.length) 且仅用 O(n + m) 额外空间的算法来解决此问题吗？
 */
public class OddCells {

    public int oddCells(int m, int n, int[][] indices) {
        int[][] marks = new int[m][n];
        //1，模拟增量操作
        int length = indices.length;
        for (int i = 0; i < length; i++) {
            int r = indices[i][0];
            int c = indices[i][1];
            for (int j = 0; j < n; j++) {
                marks[r][j]++;
            }
            for (int j = 0; j < m; j++) {
                marks[j][c]++;
            }
        }
        //2，统计奇数个数
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (marks[i][j] % 2 == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
