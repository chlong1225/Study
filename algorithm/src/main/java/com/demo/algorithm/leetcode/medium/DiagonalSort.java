package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * create on 2024/4/29
 * @author chenglong
 * description : 将矩阵按对角线排序
 *
 * 矩阵对角线是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。
 * 例如，矩阵mat有6行3列，从mat[2][0]开始的矩阵对角线将会经过mat[2][0]、mat[3][1]和mat[4][2]。
 * 给你一个m * n的整数矩阵mat，请你将同一条矩阵对角线上的元素按升序排序后，返回排好序的矩阵。
 *
 * 示例 1：
 * 输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * 输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 *
 * 示例 2：
 * 输入：mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
 * 输出：[[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class DiagonalSort {

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        List<Integer> dates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = 0;
            int y = i;
            dates.clear();
            while (x < m && y < n) {
                dates.add(mat[x][y]);
                x++;
                y++;
            }
            Collections.sort(dates);
            x = 0;
            y = i;
            int index = 0;
            while (x < m && y < n) {
                mat[x][y] = dates.get(index);
                index++;
                x++;
                y++;
            }
        }
        for (int i = 1; i < m; i++) {
            int x = i;
            int y = 0;
            dates.clear();
            while (x < m && y < n) {
                dates.add(mat[x][y]);
                x++;
                y++;
            }
            Collections.sort(dates);
            x = i;
            y = 0;
            int index = 0;
            while (x < m && y < n) {
                mat[x][y] = dates.get(index);
                index++;
                x++;
                y++;
            }
        }
        return mat;
    }
}
