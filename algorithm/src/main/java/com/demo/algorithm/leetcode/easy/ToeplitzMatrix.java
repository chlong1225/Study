package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2023/12/3
 * description : 托普利茨矩阵
 *
 * 给你一个m x n的矩阵matrix。如果这个矩阵是托普利茨矩阵，返回true；否则，返回false。
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是托普利茨矩阵。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * 输出：true
 * 解释：
 * 在上述矩阵中, 其对角线为:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 * 各条对角线上的所有元素均相同, 因此答案是 True 。
 *
 * 示例 2：
 * 输入：matrix = [[1,2],[2,2]]
 * 输出：false
 * 解释：
 * 对角线 "[1, 2]" 上的元素不同。
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 */
public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //1，检查第一行为起始
        for (int i = 0; i < n; i++) {
            int compare = matrix[0][i];
            for (int j = 1; j < m; j++) {
                if (i + j < n) {
                    int tem = matrix[j][i + j];
                    if (tem != compare) {
                        return false;
                    }
                }
            }
        }
        //2，检查第一列为起始
        for (int i = 1; i < m; i++) {
            int compare = matrix[i][0];
            for (int j = 1; j < n; j++) {
                if (i + j < m) {
                    int tem = matrix[i + j][j];
                    if (tem != compare) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
