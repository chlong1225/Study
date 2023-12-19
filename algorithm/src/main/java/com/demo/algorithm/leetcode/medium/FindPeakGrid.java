package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/19
 * @author chenglong
 * description : 寻找峰值II
 *
 * 一个2D网格中的峰值是指那些严格大于其相邻格子(上、下、左、右)的元素。
 * 给你一个从0开始编号的m x n矩阵mat，其中任意两个相邻格子的值都不相同。找出任意一个峰值mat[i][j]并返回其位置[i,j]。
 * 你可以假设整个矩阵周边环绕着一圈值为-1的格子。
 * 要求必须写出时间复杂度为O(m log(n))或O(n log(m))的算法
 *
 * 示例 1:
 * 输入: mat = [[1,4],[3,2]]
 * 输出: [0,1]
 * 解释: 3 和 4 都是峰值，所以[1,0]和[0,1]都是可接受的答案。
 *
 * 示例 2:
 * 输入: mat = [[10,20,15],[21,30,14],[7,16,32]]
 * 输出: [1,1]
 * 解释: 30 和 32 都是峰值，所以[1,1]和[2,2]都是可接受的答案。
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 10^5
 * 任意两个相邻元素均不相等.
 */
public class FindPeakGrid {

    private static final int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean hasBig = false;
                for (int k = 0; k < offsets.length; k++) {
                    int nx = i + offsets[k][0];
                    int ny = j + offsets[k][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        if (mat[nx][ny] > mat[i][j]) {
                            hasBig = true;
                            break;
                        }
                    }
                }
                if (!hasBig) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }
}
