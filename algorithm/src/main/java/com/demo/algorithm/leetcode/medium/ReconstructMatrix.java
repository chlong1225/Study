package com.demo.algorithm.leetcode.medium;


import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/6/29
 * @author chenglong
 * description : 重构2行二进制矩阵
 *
 * 给你一个2行n列的二进制数组：
 * 矩阵是一个二进制矩阵，这意味着矩阵中的每个元素不是0就是1。
 * 第0行的元素之和为upper。
 * 第1行的元素之和为lower。
 * 第i列（从0开始编号）的元素之和为colsum[i]，colsum是一个长度为n的整数数组。
 * 你需要利用upper，lower和colsum来重构这个矩阵，并以二维整数数组的形式返回它。
 * 如果有多个不同的答案，那么任意一个都可以通过本题。
 * 如果不存在符合要求的答案，就请返回一个空的二维数组。
 *
 * 示例 1：
 * 输入：upper = 2, lower = 1, colsum = [1,1,1]
 * 输出：[[1,1,0],[0,0,1]]
 * 解释：[[1,0,1],[0,1,0]] 和 [[0,1,1],[1,0,0]] 也是正确答案。
 *
 * 示例 2：
 * 输入：upper = 2, lower = 3, colsum = [2,2,1,1]
 * 输出：[]
 *
 * 示例 3：
 * 输入：upper = 5, lower = 5, colsum = [2,1,2,0,1,0,1,2,0,1]
 * 输出：[[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]
 *
 * 提示：
 * 1 <= colsum.length <= 10^5
 * 0 <= upper, lower <= colsum.length
 * 0 <= colsum[i] <= 2
 */
public class ReconstructMatrix {

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        //1，判断是否存在符合条件的数组
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += colsum[i];
        }
        if (sum != (upper + lower)) {
            return new ArrayList<>();
        }
        int[][] dates = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 0) {
                dates[i][0] = 0;
                dates[i][1] = 0;
            } else if (colsum[i] == 2) {
                dates[i][0] = 1;
                dates[i][1] = 1;
                upper--;
                lower--;
                if (upper < 0 || lower < 0) {
                    return new ArrayList<>();
                }
            } else {
                if (upper > lower) {
                    dates[i][0] = 1;
                    dates[i][1] = 0;
                    upper--;
                } else {
                    dates[i][0] = 0;
                    dates[i][1] = 1;
                    lower--;
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            List<Integer> items = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                items.add(dates[j][i]);
            }
            result.add(items);
        }
        return result;
    }
}
